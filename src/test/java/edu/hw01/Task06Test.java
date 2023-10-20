package edu.hw01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Постоянная Капрекара")
public class Task06Test {
    @Test
    @DisplayName("Число Капрекара")
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
    @DisplayName("Пример 4")
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
    @DisplayName("Пример 3")
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
    @DisplayName("Пример 2")
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
    @DisplayName("Пример 1")
    void f83524(){
        //given
        int number = 3524;
        //when
        int count = Task06.countK(number);
        //then
        assertThat(count)
            .isEqualTo(3);
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


    @Test
    @DisplayName("Проход по всем значеням")
    void f32312(){
        Logger LOGGER = LogManager.getLogger();
        int[] stat = new int[8];
        for (int i = 1001; i < 10000; i++) {
            if (i % 1111 == 0) continue;
            stat[Task06.countK(i)]++;
        }

        for (int i = 0; i < 8; i++) {
            LOGGER.info("количество шагов " + i + "\tколичсетво значений " + stat[i]);
        }
    }
}
