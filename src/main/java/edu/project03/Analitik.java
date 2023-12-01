package edu.project03;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private static final Pattern PATTERN = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - (.*) \\[(.*)\\] \\\"(.*)\\\" (\\d{1,}) (\\d{1,}) \\\"(.*)\\\" \\\"(.*)\\\"");

    private static final Map<String, String> CODE_NAMES = Map.ofEntries(
        Map.entry("200", "OK"),
        Map.entry("404", "Not Found"),
        Map.entry("500", "Internal Server Error")
    );
    private static final ArrayList<String> params = new ArrayList<>(4){{
        add("--path");
        add("--from");
        add("--to");
        add("--format");
    }};
    private long count = 0;
    private long size = 0;
    HashMap<String, Long> res = new HashMap<>();
    HashMap<String, Long> status = new HashMap<>();

    private ArrayList<Path> paths = new ArrayList<>();
    private OffsetDateTime from;
    private OffsetDateTime to;
    private Format format;
    public static void main(String[] args) {
        new Analitik(args).anal();
    }
    private Analitik (String[] args) {
        from = OffsetDateTime.MIN;
        to = OffsetDateTime.MAX;
        format = new Markdown();

        ArrayList<ArrayList<String>> argguments = new ArrayList<>(4){{
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
        }};
        int last = -1;
        for (String arg : args) {
            if (params.contains(arg)) {
                last = params.indexOf(arg);
            } else if (!arg.isEmpty()){
                argguments.get(last).add(arg);
            }
        }

        if (argguments.get(0).isEmpty()) {
            throw new RuntimeException("Enter path to log file");
        }

        for (String a : argguments.get(0).toArray(new String[0])) {
            paths.add(Paths.get(a));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var from = argguments.get(1);
        if (!from.isEmpty()) {
            var s = from.get(from.size() - 1);
            this.from = OffsetDateTime.of(LocalDate.parse(s, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
        }
        var to = argguments.get(2);
        if (!to.isEmpty()) {
            var s = to.get(to.size() - 1);
            this.to = OffsetDateTime.of(LocalDate.parse(s, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
        }
        if (!argguments.get(3).isEmpty()) {
            switch (argguments.get(3).get(argguments.get(3).size() - 1)) {
                case "markdown" -> {
                    this.format = new Markdown();
                }
                case "adoc" -> {
                    this.format = new Adoc();
                }
            }
        }
    }

    public void anal() {
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
                current = OffsetDateTime.parse(matcher.group(3), formatter);
                var t1 = Duration.between(from, current).toSeconds() > 0;
                var t2 = Duration.between(current, to).toSeconds() > 0;
                while (t1 && t2) {
                    count++;
                    var resource = matcher.group(4);
                    if (res.containsKey(resource)) {
                        res.put(resource, res.get(resource) + 1);
                    } else {
                        res.put(resource, 1L);
                    }
                    var code = matcher.group(5);
                    if (status.containsKey(code)) {
                        status.put(code, status.get(code) + 1);
                    } else {
                        status.put(code, 1L);
                    }
                    size += Long.parseLong(matcher.group(6));


                    line = readLine(in);
                    if (line.isEmpty()) {
                        break;
                    }
                    matcher = PATTERN.matcher(line);
                    matcher.find();
                    current = OffsetDateTime.parse(matcher.group(3), formatter);
                    t1 = Duration.between(from, current).toSeconds() > 0;
                    t2 = Duration.between(current, to).toSeconds() > 0;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        report();
    }

    private void report() {
        try (var out = Files.newOutputStream(Paths.get("REPORT" + format.extension()))) {
            ArrayList<Table> tables = new ArrayList<>();

            Table table = new Table("Общая информация");
            table.add(new String[]{"Метрика", "Значение"});
            table.add(new String[]{"Файл(-ы)", Arrays.toString(paths.toArray(new Path[0]))});
            table.add(new String[]{"Начальная дата", from == OffsetDateTime.MIN ? "-" : from.toString()});
            table.add(new String[]{"Конечная дата", to == OffsetDateTime.MAX ? "-" : to.toString()});
            table.add(new String[]{"Количество запросов", String.valueOf(count)});
            table.add(new String[]{"Средний размер ответа", String.valueOf(count != 0 ?size / count : 0)});
            tables.add(table);

            table = new Table("Запрашиваемые ресурсы");
            table.add(new String[]{"Ресурс", "Количество"});
            for (var key : res.keySet()) {
                table.add(new String[]{key, String.valueOf(res.get(key))});
            }
            tables.add(table);

            table = new Table("Коды ответа");
            table.add(new String[]{"Код", "Имя", "Количество"});
            for (var key : status.keySet()) {
                table.add(new String[]{key, CODE_NAMES.get(key), String.valueOf(res.get(key))});
            }
            tables.add(table);

            Out.print(out, format, tables.toArray(tables.toArray(new Table[0])));
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
