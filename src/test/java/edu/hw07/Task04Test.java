package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task04Test {
    @Test
    @DisplayName("pi")
    void f1() {
        //given
        long n = 10_000_000;
        //when
        double p = Task04.pi(n, 8);
        //then
        assertThat(p).isBetween(3.14 - 0.01, 3.14 + 0.01);
    }

    @Test
    @DisplayName("Time test")
    void f2() {
        //given
        long n = 1000;
        int threads = 8;
        //when
        for (int i = 1; i <= threads; i++) {
//        for (int i = 8; i > 0; i--) {
            Instant start = Instant.now();
            Task04.pi(n, i);
            System.out.println("" + i + " - " + Duration.between(start, Instant.now()).toMillis());
        }
        /*
        1000_000_000
        1 - 12192
        2 - 47560
        3 - 48626
        4 - 50656
        5 - 58581
        6 - 94543
        7 - 92461
        8 - 126367
         */
    }
}
