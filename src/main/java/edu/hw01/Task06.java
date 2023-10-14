package edu.hw01;

import java.util.Arrays;

public class Task06 {
    private Task06(){}

    public static int countK(int number){
        if (number == 6174) return 0;
        int[] digits = new int[4];

        for (int i = 0; i < 4; i++) {
            digits[i] = number % 10;
            number /= 10;
        }

        digits = Arrays.stream(digits).sorted().toArray();

        return 1 + countK(
            1000 * (digits[3] - digits[0]) +
            100 * (digits[2] - digits[1]) +
            10 * (digits[1] - digits[2]) +
            (digits[0] - digits[3]));
    }
}
