package edu.hw03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task01Test {
    @Test
    @DisplayName("Latin letters")
    public void f1() {
        //given
        String s1 = "ABCabcZYXzyx";
        //when
        String s2 = Task01.atbash(s1);
        //then
        assertThat(s2).isEqualTo("ZYXzyxABCabc");
    }

    @Test
    @DisplayName("Russian letters")
    public void f2() {
        //given
        String s1 = "Русские буквы";
        //when
        String s2 = Task01.atbash(s1);
        //then
        assertThat(s2).isEqualTo(s1);
    }

    @Test
    @DisplayName("Not letters")
    public void f3() {
        //given
        String s1 = "[]()!90321";
        //when
        String s2 = Task01.atbash(s1);
        //then
        assertThat(s2).isEqualTo(s1);
    }
}
