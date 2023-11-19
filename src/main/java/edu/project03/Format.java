package edu.project03;

import java.util.Arrays;
import java.util.Optional;

public abstract class Format {
    private int[] sizes;
    private Assign assign;
    public abstract String handler(String handler);
    public String line(String key, String[][] strings) {
//        int countLines = -1;
//        for (String[] lines : strings) {
//            countLines = Math.max(countLines, lines.length);
//        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
        }
    }
    public String separator();

    void set(int[] size);
}
