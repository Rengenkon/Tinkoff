package edu.hw01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Длина видео")
public class Task01Test {
    @Test
    @DisplayName("Правильный ввод")
    void normal() {
        // given
        String time = "1:30";

        // when
        int secondsLength = Task01.minutesToSeconds(time);

        // then
        assertThat(secondsLength)
            .isEqualTo(90);
    }

    @Test
    @DisplayName("60 секунд")
    void outOfSecondsBounds() {
        // given
        String time = "0:60";

        // when
        int secondsLength = Task01.minutesToSeconds(time);

        // then
        assertThat(secondsLength)
            .isEqualTo(-1);
    }
    @Test
    @DisplayName("Отрицательные секунды")
    void minusSeconds() {
        // given
        String time = "2:-3";

        // when
        int secondsLength = Task01.minutesToSeconds(time);

        // then
        assertThat(secondsLength)
            .isEqualTo(-1);
    }
    @Test
    @DisplayName("Отрицательные минуты")
    void minusMinutes() {
        // given
        String time = "-5:30";

        // when
        int secondsLength = Task01.minutesToSeconds(time);

        // then
        assertThat(secondsLength)
            .isEqualTo(-1);
    }
}
