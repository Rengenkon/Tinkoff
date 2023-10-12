package edu.hw01;

public class Task05 {
    private Task05(){}

    public static boolean isPalindromeDescendant(long number){
        long c = number;
        while (c >= 10){
            if (isPalindrome(c)) return true;
            c = descendant(c);
        }
        return false;
    }
    private static boolean isPalindrome(long n){
        long tens = tens(n);
        while (n > 0){
            long l = n / tens, r = n % 10;
            if (l != r) return false;

            n %= tens;
            n /=10;
            tens /= 100;
        }
        return true;
    }

    private static long descendant(long n){
        long left = 0, right = 0;

        //как обрабатывать длину 2k + 1, 4k + 1 - нормально
        long c = n;
        int k = 0;
        while (c > 0){
            k++;
            c /= 10;
        }
        k--;
        if (k % 2 == 0 && k % 4 != 0) return -1;


        while (n > 0){
            if (n < 10){//сработает только при начальном числе из 4k + 1 цифр
                left *= 10;
                left += n;
                break;
            };

            long tens = tens(n), lc = n / tens + (n / (tens / 10)) % 10;
            long rc = 0;
            if (n > 100) rc = n % 10 + (n % 100) / 10;

            if (lc >= 10) left *= 100;
            else left *= 10;
            left += lc;

            if (right == 0) right += rc;
            else right += rc * 10 * tens(right);

            n %= (tens / 10);
            n /= 100;
        }

        if (right == 0) return left;
        return left * 10 * tens(right) + right;
    }
    private static long tens(long n){
        long c = 1;
        while (n / c >= 10){
            c *= 10;
        }
        return c;
    }
}
