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
        long n = 1000_000_000;
        int threads = 16;
        //when
        for (int i = 1; i <= threads; i++) {
//        for (int i = 8; i > 0; i--) {
            Instant start = Instant.now();
            Task04.pi(n, i);
            System.out.println("" + i + " - " + Duration.between(start, Instant.now()).toMillis());
        }
        /*
        Random
        1000_000_000
        1 - 12192
        2 - 47560
        3 - 48626
        4 - 50656
        5 - 58581
        6 - 94543
        7 - 92461
        8 - 126367

        ThreadLocalRandom
        1000_000_000
        1 - 6418
        2 - 3179
        3 - 2108
        4 - 1687
        5 - 1957
        6 - 1654
        7 - 1668
        8 - 1549
        9 - 1630
        10 - 1617
        11 - 1626
        12 - 1679
        13 - 1730
        14 - 1657
        15 - 1566
        16 - 1526
         */
    }

    @Test
    @DisplayName("Accuracy test")
    void f3() {
        //given
        int threads = 8;
        //when
        for (int i = 10_000_000; i <= 1_000_000_000; i*=10) {
            Instant start = Instant.now();
            var pi = Task04.pi(i, threads);
            System.out.println("" + i + " - " + pi);
        }
        /*
        10000000   - 3.1413932
        100000000  - 3.14137676
        1000000000 - 3.141358132
         */
    }
}
