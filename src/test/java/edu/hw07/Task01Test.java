package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task01Test {

    @Test
    @DisplayName("sa")
    void f() {
        //given
        int n = 5;
        //when
        long res = Task02.fac(n);
        //then
        assertThat(res).isEqualTo(120);
    }
}
