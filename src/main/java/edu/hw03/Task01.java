package edu.hw03;

public class Task01 {
    public static String atbash(String string) {
        StringBuilder edit = new StringBuilder(string);
        char c;

        for (int i = 0; i < edit.length(); i++) {
            c = edit.charAt(i);
            if ('A' <= c && c <= 'Z') {
                edit.setCharAt(i, (char) ('A' + ('Z' - c)));
            } else if ('a' <= c && c <= 'z') {
                edit.setCharAt(i, (char) ('a' + ('z' - c)));
            }
        }

        return edit.toString();
    }
}
