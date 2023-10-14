package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Количество цифр")
public class Task02Test {
    @Test
    @DisplayName("Ноль")
    void nul (){
        //given
        long number = 0;
        //when
        int count = Task02.countDigits(number);
        //then
        assertThat(count)
            .isEqualTo(1);
    }
    @Test
    @DisplayName("Отрицательное число")
    void plus (){
        //given
        long number = -987654321;
        //when
        int count = Task02.countDigits(number);
        //then
        assertThat(count)
            .isEqualTo(9);
    }
    @Test
    @DisplayName("Положительное число")
    void minus (){
        //given
        long number = 1234567890;
        //when
        int count = Task02.countDigits(number);
        //then
        assertThat(count)
            .isEqualTo(10);
    }
}
