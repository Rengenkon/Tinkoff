package edu.project03;

import java.io.IOException;
import java.io.OutputStream;

public class Out {
    public void print(OutputStream out, Format format, Table[] tables) {
        try {
            for (Table table : tables) {
                out.write(format.handler(table.getHandler()).getBytes());
                format.set(table.getSizes());
                var map = table.getLines();
                for (String key : map.keySet()) {
                    out.write(format.line(key, map.get(key)).getBytes());
                    out.write(format.separator().getBytes());
                }
                out.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
