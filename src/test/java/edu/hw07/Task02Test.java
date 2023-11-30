package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task02Test {

    @Test
    @DisplayName("5")
    void f1() {
        //given
        int n = 5;
        //when
        BigInteger res = Task02.fac(n);
        //then
        assertThat(res).isEqualTo(BigInteger.valueOf(120));
    }

    @Test
    @DisplayName("1")
    void f2() {
        //given
        int n = 1;
        //when
        BigInteger res = Task02.fac(n);
        //then
        assertThat(res).isEqualTo(BigInteger.ONE);
    }

    @Test
    @DisplayName("-1")
    void f3() {
        //given
        int n = -1;
        //when
        assertThrows(RuntimeException.class,() -> {
            BigInteger res = Task02.fac(n);
        });
        //then
    }

    @Test
    @DisplayName("30")
    void f4() {
        //given
        int n = 30;
        //when
        BigInteger res = Task02.fac(n);
        //then
        assertThat(res).isEqualTo(new BigInteger("265252859812191058636308480000000"));
    }
}
