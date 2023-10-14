package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Постоянная Капрекара")
public class Task06Test {
    @Test
    @DisplayName("6174")
    void f6174(){
        //given
        int number = 6174;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(0);
    }
    @Test
    @DisplayName("1234")
    void f1234(){
        //given
        int number = 1234;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(3);
    }
    @Test
    @DisplayName("6554")
    void f6554(){
        //given
        int number = 6554;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(4);
    }
    @Test
    @DisplayName("6621")
    void f6621(){
        //given
        int number = 6621;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(5);
    }
    @Test
    @DisplayName("8073")
    void f8073(){
        //given
        int number = 8073;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(2);
    }
    @Test
    @DisplayName("3258")
    void f3258(){
        //given
        int number = 3258;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(1);
    }
}
