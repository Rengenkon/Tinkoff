package edu.project03;

import java.io.IOException;
import java.io.OutputStream;

public class Out {
    public static void print(OutputStream out, Format format, Table... tables){
        print(out, format, Align.Center, tables);
    }
    public static void print(OutputStream out, Format format, Align align, Table... tables) {
        try {
            for (Table table : tables) {
                for (var line : format.getTable(table, align)) {
                    out.write(line.getBytes());
                }
                out.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
