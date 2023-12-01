package edu.project03;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analitik {
    private static final String Ip_Pattern = "(\\d{1,3}\\.){3}\\d{1,3}|(\\w{0,4}:){2,7}\\w{0,4}";
    public static final Pattern PATTERN = Pattern.compile(
        "(" + Ip_Pattern + ") - (" + Ip_Pattern + "|-) " +// ip - ip
             "\\[(.*)\\] " +// time
            "\\\"(.*)\\\" " +// request
            "(\\d{1,}) " +// response code
            "(\\d{1,}) " +// size
            "\\\"(.*)\\\" " +// http_referer
            "\\\"(.*)\\\"");// user_agent
    public static class Groups{
        private static final int InnerGroupsIP = 2;

        static final int FirstIP = 1;
        static final int SecondIP = FirstIP + InnerGroupsIP + 1;
        static final int Time = SecondIP + InnerGroupsIP + 1;
        static final int Request = Time + 1;
        static final int ResponseCode = Request + 1;
        static final int Size = ResponseCode + 1;
        static final int Referer = Size + 1;
        static final int UserAgent = Referer + 1;
    }

    static class Parameter {
        static final int NONE = -1;
        static final int PATH = 0;
        static final int FROM = 1;
        static final int TO = 2;
        static final int FORMAT = 3;
    }

    private static final Map<String, String> CODE_NAMES = Map.ofEntries(
        Map.entry("200", "OK"),
        Map.entry("404", "Not Found"),
        Map.entry("500", "Internal Server Error")
    );

    private final ArrayList<Path> paths = new ArrayList<>();
    private OffsetDateTime from;
    private OffsetDateTime to;
    private Format format;

    private long countRequests = 0;
    private long sumSize = 0;
    HashMap<String, Long> resources = new HashMap<>();
    HashMap<String, Long> status = new HashMap<>();

    public static void main(String[] args) {
        new Analitik(args).anal();
    }

    private Analitik (String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        from = OffsetDateTime.MIN;
        to = OffsetDateTime.MAX;
        format = new Markdown();
        int parameter = Parameter.NONE;

        for (String arg : args) {
            switch (arg) {
                case "--path" -> {
                    parameter = Parameter.PATH;
                }
                case "--from" -> {
                    parameter = Parameter.FROM;
                }
                case "--to" -> {
                    parameter = Parameter.TO;
                }
                case "--format" -> {
                    parameter = Parameter.FORMAT;
                }
                default -> {
                    if (!arg.isEmpty()){
                        switch (parameter) {
                            case Parameter.PATH -> {
                                paths.add(Paths.get(arg));
                            }
                            case Parameter.FROM -> {
                                from = OffsetDateTime.of(LocalDate.parse(arg, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
                            }
                            case Parameter.TO -> {
                                to = OffsetDateTime.of(LocalDate.parse(arg, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
                            }
                            case Parameter.FORMAT -> {
                                switch (arg.toLowerCase()) {
                                    case "markdown" -> this.format = new Markdown();
                                    case "adoc" -> this.format = new Adoc();
                                    default -> throw new RuntimeException("Argument error: unknown format");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (paths.isEmpty()) {
            throw new RuntimeException("Enter path to logfile");
        }
    }

    private void anal() {
        parsing();
        report();
    }

    private void parsing() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        String line;
        Matcher matcher;
        OffsetDateTime current;
        for (Path path : paths) {
            try (var in = Files.newInputStream(path)) {
                line = readLine(in);
                if (line.isEmpty()) {
                    continue;
                }
                matcher = PATTERN.matcher(line);
                matcher.find();
                current = OffsetDateTime.parse(matcher.group(Groups.Time), formatter);
                while (Duration.between(from, current).toSeconds() > 0 && Duration.between(current, to).toSeconds() > 0) {
                    editRecords(matcher);
                    line = readLine(in);
                    if (line.isEmpty()) {
                        break;
                    }
                    matcher = PATTERN.matcher(line);
                    if (!matcher.find()){
                        throw new RuntimeException("Parse error.\nFile: " + path + "\n Line number:" + countRequests
                        + "\nLine: " + line);
                    }
                    current = OffsetDateTime.parse(matcher.group(Groups.Time), formatter);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void editRecords(Matcher matcher) {
        countRequests++;
        var resource = matcher.group(Groups.Request);
        if (resources.containsKey(resource)) {
            resources.put(resource, resources.get(resource) + 1);
        } else {
            resources.put(resource, 1L);
        }
        var code = matcher.group(Groups.ResponseCode);
        if (status.containsKey(code)) {
            status.put(code, status.get(code) + 1);
        } else {
            status.put(code, 1L);
        }
        sumSize += Long.parseLong(matcher.group(Groups.Size));
    }

    private void report() {
        Table information = new Table("Общая информация");
        information.add("Метрика", "Значение");
        information.add("Файл(-ы)", Arrays.toString(paths.toArray(new Path[0])));
        information.add("Начальная дата", from == OffsetDateTime.MIN ? "-" : from.toString());
        information.add("Конечная дата", to == OffsetDateTime.MAX ? "-" : to.toString());
        information.add("Количество запросов", String.valueOf(countRequests));
        information.add("Средний размер ответа", String.valueOf(countRequests != 0 ? sumSize / countRequests : 0));

        Table resources = new Table("Запрашиваемые ресурсы");
        resources.add("Ресурс", "Количество");
        for (var key : this.resources.keySet()) {
            resources.add(key, String.valueOf(this.resources.get(key)));
        }

        Table codes = new Table("Коды ответа");
        codes.add("Код", "Имя", "Количество");
        for (var key : status.keySet()) {
            codes.add(key, CODE_NAMES.get(key), status.get(key).toString());
        }

        try (var out = Files.newOutputStream(format.getFile())) {
            Out.print(out, format,
                information, resources, codes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readLine (InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int c = stream.read();
        while (c != -1 && c != (int)'\n') {
            builder.append((char) c);
            c = stream.read();
        }
        return builder.toString();
    }
}
