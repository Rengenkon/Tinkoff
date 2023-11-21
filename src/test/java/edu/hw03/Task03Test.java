package edu.hw03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task03Test {
    @Test
    @DisplayName("Empty")
    public void f1() {
        //given
        String[] s1 = {};
        //when
        Task03<String> t = new Task03<>();
        HashMap<String, Integer> s2 = t.freqDict(s1);
        //then
        assertThat(s2).isEqualTo(Map.ofEntries());
    }

    @Test
    @DisplayName("String")
    public void f2() {
        //given
        String[] s1 = {"a", "a", "a", "a"};
        //when
        Task03<String> t = new Task03<>();
        HashMap<String, Integer> s2 = t.freqDict(s1);
        //then
        assertThat(s2).isEqualTo(Map.ofEntries(
            Map.entry("a", 4)
        ));
    }

    @Test
    @DisplayName("Integer")
    public void f3() {
        //given
        Integer[] s1 = {1, 2, 3, 1};
        //when
        Task03<Integer> t = new Task03<>();
        HashMap<Integer, Integer> s2 = t.freqDict(s1);
        //then
        assertThat(s2).isEqualTo(Map.ofEntries(
            Map.entry(1, 2),
            Map.entry(2, 1),
            Map.entry(3, 1)
        ));
    }
}
