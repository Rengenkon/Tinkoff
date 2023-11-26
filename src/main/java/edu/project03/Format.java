package edu.project03;

import java.util.ArrayList;

public abstract class Format {
    public abstract String extension();
    public String[] getTable(Table table, Align align) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(handler(table));
        for (String[] line : table.getLines()) {
            lines.add(separator(table.getSizes()));
            lines.add(line(line, align, table.getSizes()));
        }
        lines.add(separator(table.getSizes()));
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
                        .append(line[i]).
                        repeat(' ', ((size - line[i].length()) / 2 + (size - line[i].length()) % 2));
                }
                case Right -> {
                    builder.repeat(' ', size - line[i].length())
                        .append(line[i]);
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
        if (!builder.isEmpty()){
            builder.append("+\n");
        }
        return builder.toString();
    }
}
