package edu.project03;

import java.io.IOException;
import java.io.OutputStream;

public class Out {
    public static void print(OutputStream out, Format format, Table... tables) {
        try {
            for (Table table : tables) {
                for (var l : format.getTable(table, Assign.Center)) {
                    out.write(l.getBytes());
                }
                out.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
