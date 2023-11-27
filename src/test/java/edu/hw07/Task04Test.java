package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task04Test {
    @Test
    @DisplayName("sa")
    void f() {
        //given
        long n = 10_000_000;
        //when
        double p = Task04.pi(n);
        //then
        assertThat(p).isEqualTo(3.14);
    }
}
