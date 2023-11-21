package edu.hw03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task02Test {
    @Test
    @DisplayName("Base")
    public void f1() {
        //given
        String s1 = "()()(())";
        //when
        String[] s2 = Task02.clusterize(s1);
        //then
        assertThat(s2).hasSize(3).contains(
            "()",
            "()",
            "(())"
        );
    }

    @Test
    @DisplayName("Opening brackets")
    public void f2() {
        //given
        String s1 = "(((";
        //when
        String[] s2 = Task02.clusterize(s1);
        //then
        assertThat(s2).hasSize(0).contains();
    }

    @Test
    @DisplayName("Closing brackets")
    public void f3() {
        //given
        String s1 = ")))";
        //when
        String[] s2 = Task02.clusterize(s1);
        //then
        assertThat(s2).hasSize(0).contains();
    }

    @Test
    @DisplayName("Contains text")
    public void f4() {
        //given
        String s1 = "(!something^)";
        //when
        String[] s2 = Task02.clusterize(s1);
        //then
        assertThat(s2).hasSize(1).contains(
            "(!something^)"
        );
    }
}
