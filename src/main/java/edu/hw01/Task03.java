package edu.hw01;

import java.util.Arrays;
import java.util.OptionalInt;

public class Task03 {
    private Task03(){}

    public static boolean isNestable(int[] a, int[] b){
//        if (a == null || b == null) return false;
        OptionalInt min1 = Arrays.stream(a).min();
        OptionalInt min2 = Arrays.stream(b).min();
        OptionalInt max1 = Arrays.stream(a).max();
        OptionalInt max2 = Arrays.stream(b).max();

        if (min1.isEmpty() || min2.isEmpty()) return false;
        if (min1.getAsInt() <= min2.getAsInt()) return false;
        if (max1.getAsInt() >= max2.getAsInt()) return false;

        return true;
    }
}
