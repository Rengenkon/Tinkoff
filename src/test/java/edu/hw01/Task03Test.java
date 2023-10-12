package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task03Test {
    @Test
    @DisplayName("Элемент из промежутка")
    void base(){
        //given
        int[] a= new int[]{1};
        int[] b = new int[]{0, 2};
        //when
        boolean nestable = Task03.isNestable(a, b);
        //then
        assertThat(nestable)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Грнаицы равны элементу")
    void oneDigit(){
        //given
        int[] a= new int[]{1};
        int[] b = new int[]{1, 1};
        //when
        boolean nestable = Task03.isNestable(a, b);
        //then
        assertThat(nestable)
            .isEqualTo(false);
    }
    @Test
    @DisplayName("Не сортираванный массив")
    void nestable(){
        //given
        int[] a= new int[]{2, 1, 3};
        int[] b = new int[]{4, 0};
        //when
        boolean nestable = Task03.isNestable(a, b);
        //then
        assertThat(nestable)
            .isEqualTo(true);
    }
    @Test
    @DisplayName("Выход за границу")
    void f(){
        //given
        int[] a= new int[]{1, 3, 7, 2, 11, 2};
        int[] b = new int[]{0, 10};
        //when
        boolean nestable = Task03.isNestable(a, b);
        //then
        assertThat(nestable)
            .isEqualTo(false);
    }
}
