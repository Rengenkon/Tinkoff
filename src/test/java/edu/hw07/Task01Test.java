package edu.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task01Test {
    @Test
    @DisplayName("Add 1")
    void f1() {
        //given
        Task01 task01 = new Task01();
        int a = 1;
        int res = a * Task01.TREAD_COUNT;
        //when
        int ans = task01.add(a);
        //then
        assertThat(res).isEqualTo(ans);
    }

    @Test
    @DisplayName("Add 1000")
    void f2() {
        //given
        Task01 task01 = new Task01();
        int a = 1000;
        int res = a * Task01.TREAD_COUNT;
        //when
        int ans = task01.add(a);
        //then
        assertThat(res).isEqualTo(ans);
    }
}
