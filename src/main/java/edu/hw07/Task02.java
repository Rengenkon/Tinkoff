package edu.hw07;

import java.util.stream.IntStream;

public class Task02 {
    public static long fac(int n) {
        return IntStream.rangeClosed(1, n).parallel().reduce(1, (a, b) -> a * b);
    }
}
