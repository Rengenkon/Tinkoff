package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task02Test {
    @Test
    @DisplayName("sa")
    void f() {
        //given
        Task01 task01 = new Task01();
        int a = 1;
        int res = a * Task01.TREAD_COUNT;
        //when
        int ans = task01.add(a);
        //then
        assertThat(res).isEqualTo(ans);
    }
}
