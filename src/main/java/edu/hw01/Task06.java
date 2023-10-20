package edu.hw01;

import java.util.Arrays;

public class Task06 {
    private static final int DIGIT = 4;
    private static final int NUMBER_SYSTEM = 10;
    private static final int KAPREKAR = 6174;
    private static final int INF_VALUE = 1111;

    private Task06() {}

    public static int countK(int num) {
        if (!valid(num)) {
            return -1;
        }

        int number = num;

        if (number == KAPREKAR) {
            return 0;
        }

        int[] digits = new int[DIGIT];

        for (int i = 0; i < DIGIT; i++) {
            digits[i] = number % NUMBER_SYSTEM;
            number /= NUMBER_SYSTEM;
        }

        digits = Arrays.stream(digits).sorted().toArray();

        int nextNumber = 0;
        int dec = 1;

        for (int i = 0; i < DIGIT; i++) {
            nextNumber += dec * (digits[i] - digits[DIGIT - 1 - i]);
            dec *= NUMBER_SYSTEM;
        }

        return 1 + countK(nextNumber);
    }

    private static boolean valid(int num) {
        int t = NUMBER_SYSTEM * NUMBER_SYSTEM * NUMBER_SYSTEM;
        if (num <= t || num >= t * NUMBER_SYSTEM) {
            return false;
        }
        if (num % INF_VALUE == 0) {
            return false;
        }
        return true;
    }
}
