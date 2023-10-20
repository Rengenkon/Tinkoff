package edu.hw01;

public class Task02 {
    private static final int NUMBER_SYSTEM = 10;

    private Task02() {}

    public static int countDigits(long number) {
        long num = number;
        int count  = 0;

        do {
            count++;
            num /= NUMBER_SYSTEM;
        } while (num != 0);

        return count;
    }
}
