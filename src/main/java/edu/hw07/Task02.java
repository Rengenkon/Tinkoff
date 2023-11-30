package edu.hw07;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Task02 {

    private Task02() {}

    public static BigInteger fac(int n) {
        if (n < 1) {
            throw new RuntimeException("Given n less than 1");
        }
        return IntStream.rangeClosed(1, n).mapToObj(BigInteger::valueOf).parallel()
            .reduce(BigInteger.ONE, (a, b) -> b.multiply(a));
    }
}
