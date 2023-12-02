package edu.project03;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;

public class Analytical {
    Settings settings;
    private long countRequests = 0;
    private long sumSize = 0;
    HashMap<String, Long> resources = new HashMap<>();
    HashMap<String, Long> status = new HashMap<>();

    public static void main(String[] args) {
        new Analytical(args).anal();
    }

    private Analytical(String[] args) {
        settings = new Settings(args);
    }

    private void anal() {
        for (Path path : settings.getPaths()) {
            try {
                if (path.toString().startsWith("http")) {
                    //сокеты

                } else {
                    try (var in = Files.newInputStream(path)) {
                        parsing(in);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (PatternNginx.PatternNotMatch e) {
                e.add("\nFile path: " + path);
                throw new RuntimeException(e.get());
            }
        }
        report();
    }

    private void parsing(InputStream in) throws IOException, PatternNginx.PatternNotMatch {
        String line = readLine(in);
        if (line.isEmpty()) {
            return;
        }
        try {
            PatternNginx nginx = PatternNginx.getMPattern(line);
            OffsetDateTime current = nginx.getTime();
            while (Duration.between(settings.getFrom(), current).toSeconds() > 0
                && Duration.between(current, settings.getTo()).toSeconds() > 0) {
                editRecords(nginx);
                line = readLine(in);
                if (line.isEmpty()) {
                    break;
                }
                nginx = PatternNginx.getMPattern(line);
                current = nginx.getTime();
            }
        } catch (PatternNginx.PatternNotMatch e) {
            e.add("\nLine: " + line
                + "\nLine's number: " + (countRequests + 1));
            throw e;
        }
    }

    private void editRecords(PatternNginx nginx) {
        countRequests++;
        var resource = nginx.getRequest();
        if (resources.containsKey(resource)) {
            resources.put(resource, resources.get(resource) + 1);
        } else {
            resources.put(resource, 1L);
        }
        var code = nginx.getResponseCOde();
        if (status.containsKey(code)) {
            status.put(code, status.get(code) + 1);
        } else {
            status.put(code, 1L);
        }
        sumSize += Long.parseLong(nginx.getSize());
    }

    private void report() {
        Table information = new Table("Общая информация");
        information.add("Метрика", "Значение");
        information.add("Файл(-ы)",
            Arrays.toString(settings.getPaths().toArray(new Path[0])));
        information.add("Начальная дата",
            settings.getFrom() == OffsetDateTime.MIN ? "-" : settings.getFrom().toString());
        information.add("Конечная дата",
            settings.getTo() == OffsetDateTime.MAX ? "-" : settings.getTo().toString());
        information.add("Количество запросов",
            String.valueOf(countRequests));
        information.add("Средний размер ответа",
            String.valueOf(countRequests != 0 ? sumSize / countRequests : 0));

        Table esourcesReq = new Table("Запрашиваемые ресурсы");
        esourcesReq.add("Ресурс", "Количество");
        for (var key : this.resources.keySet()) {
            esourcesReq.add(key, String.valueOf(this.resources.get(key)));
        }

        Table codes = new Table("Коды ответа");
        codes.add("Код", "Имя", "Количество");
        for (var key : status.keySet()) {
            codes.add(key, getCodeName(key), status.get(key).toString());
        }

        try (var out = Files.newOutputStream(settings.getFormat().getFile())) {
            Out.print(out, settings.getFormat(),
                information, esourcesReq, codes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readLine(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        int c = stream.read();
        while (c != -1 && c != (int) '\n') {
            builder.append(Character.toChars(c));
            c = stream.read();
        }
        return builder.toString();
    }

    private static String getCodeName(String code) {
        switch (code) {
            case "200" -> {
                return "OK";
            }
            case "404" -> {
                return "Not Found";
            }
            case "500" -> {
                return "Internal Server Error";
            }
            default -> {
                return "";
            }
        }
    }
}
