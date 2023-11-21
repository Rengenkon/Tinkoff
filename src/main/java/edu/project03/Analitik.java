package edu.project03;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analitik {

    private static final Pattern PATTERN = Pattern.compile("'(\\w{1,}) - (\\w{1,}) \\[(\\w{1,})\\] ' '\"(\\w{1,})\" (\\w{1,}) (\\w{1,}) ' \"(\\w{1,})\" \"(\\w{1,})\"'");
    private static final Map<String, String> CODE_NAMES = Map.ofEntries(
        Map.entry("200", "OK"),
        Map.entry("404", "Not Found"),
        Map.entry("500", "Internal Server Error")
    );
    private long count = 0;
    private long size = 0;
    HashMap<String, Long> res = new HashMap<>();
    HashMap<String, Long> status = new HashMap<>();

    private Path[] paths;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private final Format format;
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
    }
    private Analitik (String[] args) {
        from = OffsetDateTime.MIN;
        to = OffsetDateTime.MAX;
        format = new Markdown();


    }

    public void anal() {
        String line;
        Matcher matcher;
        OffsetDateTime current;
        for (Path path : paths) {
            try (var in = Files.newInputStream(path)) {
                line = readLine(in);
                matcher = PATTERN.matcher(line);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                current = OffsetDateTime.of(LocalDateTime);
                while (Duration.between(from, current).toSeconds() > 0 && Duration.between(current, to).toSeconds() < 0) {
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
            table.add(new String[]{"Файл(-ы)", Arrays.toString(paths)});
            table.add(new String[]{"Начальная дата", from == OffsetDateTime.MIN ? "-" : from.toString()});
            table.add(new String[]{"Конечная дата", to == OffsetDateTime.MIN ? "-" : to.toString()});
            table.add(new String[]{"Количество запросов", String.valueOf(count)});
            table.add(new String[]{"Средний размер ответа", String.valueOf(size / count)});
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
