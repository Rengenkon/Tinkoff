package edu.hw01;

public class Task05 {

    private static final int NUMBER_SYSTEM = 10;
    private static final int FOUR = 4;

    private Task05() {}

    public static boolean isPalindromeDescendant(long number) {
        int[] c = numbers(number);

        while (c.length > 1) {
            if (isPalindrome(c)) {
                return true;
            }
            c = descendant(c);
        }
        return false;
    }

    private static boolean isPalindrome(int[] num) {
        for (int i = 0; i < num.length / 2; i++) {
            if (num[i] != num[num.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] descendant(int[] n) {
        long next = 0;
        int k = n.length;
        long lc;

        if ((k - 1) % 2 == 0 && (k - 1) % FOUR != 0) {
            //как обрабатывать длину 2k + 1?     4k + 1 - нормально
            return new int[] {};
        }

        for (int i = 0; i < k / FOUR; i++) {
            //не зайдем сдлинной 2
            lc = n[i * 2] + n[i * 2 + 1];

            if (lc >= NUMBER_SYSTEM) {
                next *= NUMBER_SYSTEM;
            }
            next *= NUMBER_SYSTEM;
            next += lc;
        }

        int shift = k / 2;

        if (k % FOUR == 1) {
            next *= NUMBER_SYSTEM;
            next += n[k / 2];
            shift++;
        }
        if (k % FOUR == 2) {
            lc = n[k / 2] + n[k / 2 - 1];

            if (lc >= NUMBER_SYSTEM) {
                next *= NUMBER_SYSTEM;
            }
            next *= NUMBER_SYSTEM;
            next += lc;
            shift++;
        }


        for (int i = 0; i < k / FOUR; i++) {
            lc = n[shift + i * 2] + n[shift + i * 2 + 1];

            if (lc >= NUMBER_SYSTEM) {
                next *= NUMBER_SYSTEM;
            }
            next *= NUMBER_SYSTEM;
            next += lc;
        }
        return numbers(next);
    }

    private static int[] numbers(long n) {

        long c = n;
        int k = 0;

        while (c > 0) {
            k++;
            c /= NUMBER_SYSTEM;
        }

        int[] numbers = new int[k];
        int i = k - 1;
        c = n;

        while (c > 0) {
            numbers[i] = (int) (c % NUMBER_SYSTEM);
            i--;
            c /= NUMBER_SYSTEM;
        }

        return numbers;
    }
}
