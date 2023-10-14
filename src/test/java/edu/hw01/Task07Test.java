package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Циклический битовый сдвиг")
public class Task07Test {
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
        int number = 0b110, shift = 1;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(0b101);
        assertThat(right)
            .isEqualTo(0b11);
    }
    @Test
    @DisplayName("30992")
    void f30992(){
        //given
        int number = 0b111100100010000, shift = 3;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(0b100100010000111);
        assertThat(right)
            .isEqualTo(0b111100100010);
    }
    @Test
    @DisplayName("10")
    void f10(){
        //given
        int number = 0b1010, shift = 10;
        //when
        int left = Task07.rotateLeft(number, shift),
            right = Task07.rotateRight(number, shift);
        //then
        assertThat(left)
            .isEqualTo(0b1010);
        assertThat(right)
            .isEqualTo(0b1010);
    }
}
