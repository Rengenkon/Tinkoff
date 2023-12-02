package edu.project03;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Format {
    String name;

    public Format() {
        name = "Report";
    }

    public Format(String fileName) {
        name = fileName;
    }

    public abstract String extension();

    public Path getFile() {
        return Paths.get(name + extension());
    }

    public Path getFile(Path folder) {
        return Paths.get(folder.toString() + name + extension());
    }

    public String[] getTable(Table table, Align align) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(handler(table));
        var separator = separator(table.getSizes());
        for (String[] line : table.getLines()) {
            lines.add(separator);
            lines.add(line(line, align, table.getSizes()));
        }
        lines.add(separator);
        return lines.toArray(lines.toArray(new String[0]));
    }

    protected String handler(Table table) {
        return table.getHandler() + "\n";
    }

    private String line(String[] line, Align align, int[] sizes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length; i++) {
            builder.append('|');
            var size = sizes[i];
            switch (align) {
                case Left -> {
                    builder.append(line[i])
                        .repeat(' ', size - line[i].length());
                }
                case Center -> {
                    builder.repeat(' ', (size - line[i].length()) / 2)
                        .append(line[i])
                        .repeat(' ', ((size - line[i].length()) / 2 + (size - line[i].length()) % 2));
                }
                case Right -> {
                    builder.repeat(' ', size - line[i].length())
                        .append(line[i]);
                }
                default -> {

                }
            }
        }
        builder.append("|\n");
        return builder.toString();
    }

    private String separator(int[] sizes) {
        StringBuilder builder = new StringBuilder();
        for (int size : sizes) {
            builder.append('+').repeat('-', size);
        }
        if (!builder.isEmpty()) {
            builder.append("+\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass() == obj.getClass());
    }
}
