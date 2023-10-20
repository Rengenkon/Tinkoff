package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Особый палиндром")
public class Task05Test {
    @Test
    @DisplayName("Число меньше 10")
    void f11(){
        //given
        long number = 1;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(false);
    }
    @Test
    @DisplayName("Палиндром")
    void f10(){
        //given
        long number = 11;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("1 преобразование")
    void f9(){
        //given
        long number = 56;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("1 преобразование")
    void f8(){
        //given
        long number = 1212;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("1 преобразование")
    void f7(){
        //given
        long number = 1267;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Большое число")
    void f6(){
        //given
        long number = 4067001103L;//4 13 0 2 3       5 3 5
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Не палендром")
    void f5(){
        //given
        long number = 19;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(false);
    }
    @Test
    @DisplayName("Не палендром с преобразованием")
    void f4(){
        //given
        long number = 123580;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(false);
    }


    //Нечетные тесты
    @Test
    @DisplayName("НЧ палендром")
    void f3(){
        //given
        long number = 12512;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("НЧ не палендром")
    void f2(){
        //given
        long number = 132;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(false);
    }
    @Test
    @DisplayName("НЧ большое преобразование")
    void f1(){
        //given
        long number = 67440121;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }


    @Test
    @DisplayName("Тест из примера 1")
    void f20(){
        //given
        long number = 11211230;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Тест из примера 2")
    void f21(){
        //given
        long number = 13001120;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Тест из примера 3")
    void f22(){
        //given
        long number = 23336014;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Правые значимые нули")
    void f23(){
        //given
        long number = 5600;
        //when
        boolean palindrome = Task05.isPalindromeDescendant(number);
        //then
        assertThat(palindrome)
            .isEqualTo(false);
    }
}
