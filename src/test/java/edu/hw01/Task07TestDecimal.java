package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Циклический битовый сдвиг")
public class Task07TestDecimal {
    @Test
    @DisplayName("1")
    void f1(){
        //given
        int number = 1, shift = 1;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(1);
        assertThat(right)
            .isEqualTo(1);
    }
    @Test
    @DisplayName("6")
    void f6(){
        //given
        int number = 6, shift = 1;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(5);
        assertThat(right)
            .isEqualTo(3);
    }
    @Test
    @DisplayName("30992")
    void f30992(){
        //given
        int number = 30992, shift = 3;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(18567);
        assertThat(right)
            .isEqualTo(3874);
    }
    @Test
    @DisplayName("10")
    void f10(){
        //given
        int number = 10, shift = 10;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(10);
        assertThat(right)
            .isEqualTo(10);
    }
}
