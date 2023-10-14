package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сломанная строка")
public class Task04Test {
    @Test
    @DisplayName("Пустая строка")
    void empty(){
        //given
        String confusing = "";
        //when
        String fixed = Task04.fixString(confusing);
        //then
        assertThat(fixed)
            .isEqualTo("");
    }
    @Test
    @DisplayName("Один символ")
    void one(){
        //given
        String confusing = "1";
        //when
        String fixed = Task04.fixString(confusing);
        //then
        assertThat(fixed)
            .isEqualTo("1");
    }
    @Test
    @DisplayName("Четное кол-во символов")
    void even(){
        //given
        String confusing = "1234";
        //when
        String fixed = Task04.fixString(confusing);
        //then
        assertThat(fixed)
            .isEqualTo("2143");
    }
    @Test
    @DisplayName("Нечетное кол-во символов")
    void odd(){
        //given
        String confusing = "12345";
        //when
        String fixed = Task04.fixString(confusing);
        //then
        assertThat(fixed)
            .isEqualTo("21435");
    }
}
