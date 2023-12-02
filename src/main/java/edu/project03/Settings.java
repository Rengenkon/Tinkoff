package edu.project03;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Settings {
    private final ArrayList<Path> paths = new ArrayList<>();
    private OffsetDateTime from;
    private OffsetDateTime to;
    private Format format;

    public Settings(String[] settings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int parameter = PARAMETERS.NONE;
        for (String arg : settings) {
            switch (arg) {
                case "--path" -> {
                    parameter = PARAMETERS.PATH;
                }
                case "--from" -> {
                    parameter = PARAMETERS.FROM;
                }
                case "--to" -> {
                    parameter = PARAMETERS.TO;
                }
                case "--format" -> {
                    parameter = PARAMETERS.FORMAT;
                }
                default -> {
                    if (!arg.isEmpty()) {
                        switch (parameter) {
                            case PARAMETERS.PATH -> {
                                paths.add(Paths.get(arg));
                            }
                            case PARAMETERS.FROM -> {
                                from = OffsetDateTime.of(
                                    LocalDate.parse(arg, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
                            }
                            case PARAMETERS.TO -> {
                                to = OffsetDateTime.of(
                                    LocalDate.parse(arg, formatter), LocalTime.of(0, 0), ZoneOffset.ofHours(0));
                            }
                            case PARAMETERS.FORMAT -> {
                                switch (arg.toLowerCase()) {
                                    case "markdown" -> {
                                        format = new Markdown();
                                    }
                                    case "adoc" -> {
                                        format = new Adoc();
                                    }
                                    default -> {
                                        throw new RuntimeException("Argument error: unknown format");
                                    }
                                }
                            }
                            default -> {

                            }
                        }
                    }
                }
            }
        }

        if (paths.isEmpty()) {
            throw new RuntimeException("Enter path to logfile");
        }
        if (from == null) {
            from = OffsetDateTime.MIN;
        }
        if (to == null) {
            to = OffsetDateTime.MAX;
        }
        if (format == null) {
            format = new Markdown();
        }
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public Format getFormat() {
        return format;
    }

    static class PARAMETERS {
        static final int NONE = -1;
        static final int PATH = 0;
        static final int FROM = 1;
        static final int TO = 2;
        static final int FORMAT = 3;
    }
}
