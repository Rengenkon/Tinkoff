package edu.hw01;

public class Task07 {
    private  Task07() {}

    public static int rotateLeft(int number, int sh) {
        int[] digits = binary(number);
        int shift = sh % digits.length;
        int[] buffer = new int[shift];

        System.arraycopy(digits, 0, buffer, 0, shift);
        for (int i = 0;  i < digits.length - shift; i++) {
            digits[i] = digits[i + shift];
        }
        System.arraycopy(buffer, 0, digits, digits.length - shift, shift);

        return numberOfDigits(digits);
    }

    public static int rotateRight(int number, int sh) {
        int[] digits = binary(number);
        int shift = sh % digits.length;
        int[] buffer = new int[shift];

        System.arraycopy(digits, digits.length - shift, buffer, 0, shift);
        for (int i = digits.length - 1;  i > shift - 1; i--) {
            digits[i] = digits[i - shift];
        }
        System.arraycopy(buffer, 0, digits, 0, shift);

        return numberOfDigits(digits);
    }

    private static int[] binary(int number) {
        int k = number;
        int size = 0;
        int num = number;

        while (k > 0) {
            size++;
            k /= 2;
        }

        int[] digits = new int[size];

        int i = 0;
        while (num > 0) {
            digits[size - 1 - i] = num % 2;
            i++;
            num /= 2;
        }

        return digits;
    }

    private static int numberOfDigits(int[] digits) {
        int number = 0;
        for (int digit : digits) {
            number *= 2;
            number += digit;
        }
        return number;
    }
}
