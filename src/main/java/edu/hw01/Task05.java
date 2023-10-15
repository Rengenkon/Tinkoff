package edu.hw01;

public class Task05 {

    private static final int NUMBER_SYSTEM = 10;
    private static final int FOUR = 4;

    private Task05() {}

    public static boolean isPalindromeDescendant(long number) {
        long c = number;
        while (c >= NUMBER_SYSTEM) {
            if (isPalindrome(c)) {
                return true;
            }
            c = descendant(c);
        }
        return false;
    }

    private static boolean isPalindrome(long num) {
        long n = num;
        long tens = tens(n);

        while (n > 0) {
            long l = n / tens;
            long r = n % NUMBER_SYSTEM;

            if (l != r) {
                return false;
            }

            n %= tens;
            n /= NUMBER_SYSTEM;
            tens /= (NUMBER_SYSTEM * NUMBER_SYSTEM);
        }
        return true;
    }

    private static long descendant(long n) {
        long left = 0;
        long right = 0;

        //как обрабатывать длину 2k + 1, 4k + 1 - нормально
        long c = n;
        int k = 0;
        while (c > 0) {
            k++;
            c /= NUMBER_SYSTEM;
        }
        k--;

        if (k % 2 == 0 && k % FOUR != 0) {
            return -1;
        }

        c = n;
        while (c > 0) {
            //сработает только при начальном числе из 4k + 1 цифр
            if (c < NUMBER_SYSTEM) {
                left *= NUMBER_SYSTEM;
                left += c;
                break;
            }

            long tens = tens(c);
            long lc = c / tens + (c / (tens / NUMBER_SYSTEM)) % NUMBER_SYSTEM;
            long rc = 0;
            if (c > (NUMBER_SYSTEM * NUMBER_SYSTEM)) {
                rc = c % NUMBER_SYSTEM + (c % (NUMBER_SYSTEM * NUMBER_SYSTEM)) / NUMBER_SYSTEM;
            }

            if (lc >= NUMBER_SYSTEM) {
                left *= (NUMBER_SYSTEM * NUMBER_SYSTEM);
            } else {
                left *= NUMBER_SYSTEM;
            }
            left += lc;

            if (right == 0) {
                right += rc;
            } else {
                right += rc * NUMBER_SYSTEM * tens(right);
            }

            c %= (tens / NUMBER_SYSTEM);
            c /= (NUMBER_SYSTEM * NUMBER_SYSTEM);
        }

        if (right == 0) {
            return left;
        }

        return left * NUMBER_SYSTEM * tens(right) + right;
    }

    private static long tens(long n) {
        long c = 1;
        while (n / c >= NUMBER_SYSTEM) {
            c *= NUMBER_SYSTEM;
        }
        return c;
    }
}
