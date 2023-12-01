package edu.project03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Table {
    private final String handler;
    private ArrayList<String[]> table = new ArrayList<>();
    private int[] size;

    Table(String name) {
        handler = name;
    }

    public void add(String... line) {
        String[] mod = line;
        for (int i = 0; i < mod.length; i++) {
            if (mod[i] == null) {
                mod[i] = "";
            }
        }
        if (size == null) {
            size = new int[mod.length];
        } else if (size.length != mod.length) {
            throw new RuntimeException("");
        }
        table.add(mod);
        for (int i = 0; i < mod.length; i++) {
            size[i] = Math.max(size[i], mod[i].length());
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
