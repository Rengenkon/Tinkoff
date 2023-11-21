package edu.project03;

import java.util.ArrayList;

public class Table {
    private final String handler;
    private ArrayList<String[]> table;
    private int[] size;

    Table(String name) {
        handler = name;
    }

    public void add(String[] line) {
        if (size == null) {
            size = new int[line.length];
        } else if (size.length != line.length) {
            throw new RuntimeException("");
        }
        table.add(line);
        for (int i =0; i < line.length; i++) {
            size[i] = Math.max(size.length, line[i].length());
        }
    }


    public String getHandler() {
        return handler;
    }

    public String[][] getLines() {
        return table.toArray(new String[0][0]);
    }

    public int[] getSizes() {
        return size;
    }
}
