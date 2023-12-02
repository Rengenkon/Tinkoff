package edu.project03;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParsingArgsTest {
    @Test
    @DisplayName("Empty args")
    void f1(){
        assertThrows(RuntimeException.class,() -> {
            new Settings();
        });
    }

    @Test
    @DisplayName("Empty path")
    void f2(){
        assertThrows(RuntimeException.class,() -> {
            new Settings("--path");
        });
    }

    @Test
    @DisplayName("One path")
    void f3(){
        //given
        String path = "a";
        Settings settings = new Settings("--path", path);
        //when
        Path[] paths = settings.getPaths().toArray(new Path[0]);

        //then
        assertThat(new Path[]{Paths.get(path)}).isEqualTo(paths);
    }

    @Test
    @DisplayName("Several path")
    void f4(){
        //given
        String[] path = new String[]{
            "a",
            "b",
            "c"
        };
        Settings settings = new Settings("--path", path[0], path[1], path[2]);
        //when
        Path[] paths = settings.getPaths().toArray(new Path[0]);
        //then
        assertThat(new Path[]{Paths.get(path[0]), Paths.get(path[1]), Paths.get(path[2])}).isEqualTo(paths);
    }

    @Test
    @DisplayName("Unknown format")
    void f5(){
        assertThrows(RuntimeException.class,() -> {
            new Settings("--path", "a", "--format", "txt");
        });
    }

    @Test
    @DisplayName("MARKDOWN")
    void f6(){
        //given
        String format = "MARKDOWN";
        Settings settings = new Settings("--path", "a", "--format", format);
        //when
        Format format1 = settings.getFormat();
        //then
        assertThat(format1).isEqualTo(new Markdown());
    }

    @Test
    @DisplayName("adoc")
    void f7(){
        //given
        String format = "adoc";
        Settings settings = new Settings("--path", "a", "--format", format);
        //when
        Format format1 = settings.getFormat();
        //then
        assertThat(format1).isEqualTo(new Adoc());
    }

    @Test
    @DisplayName("Several format")
    void f8(){
        //given
        String[] formats = new String[]{"markdown", "MARKDOWN", "ADOC"};
        Settings settings = new Settings("--path", "a", "--format", formats[0], formats[1], formats[2]);
        //when
        Format format = settings.getFormat();
        //then
        assertThat(format).isEqualTo(new Adoc());
    }

    @Test
    @DisplayName("Default format")
    void f9(){
        //given
        Settings settings = new Settings("--path", "a");
        //when
        Format format = settings.getFormat();
        //then
        assertThat(format).isEqualTo(new Markdown());
    }

    @Test
    @DisplayName("Default time")
    void f10(){
        //given
        Settings settings = new Settings("--path", "a");
        //when
        var time = settings.getFrom();
        //then
        assertThat(time).isEqualTo(OffsetDateTime.MIN);
    }

    @Test
    @DisplayName("Default time")
    void f11(){
        //given
        Settings settings = new Settings("--path", "a");
        //when
        var time = settings.getTo();
        //then
        assertThat(time).isEqualTo(OffsetDateTime.MAX);
    }

    @Test
    @DisplayName("One time")
    void f12(){
        //given
        String times = "2015-06-01";
        Settings settings = new Settings("--path", "a", "--from", times);
        //when
        var time = settings.getFrom();
        //then
        assertThat(time)
            .isEqualTo(OffsetDateTime.of(LocalDate.of(2015, 6, 1), LocalTime.of(0, 0), ZoneOffset.ofHours(0)));
    }

    @Test
    @DisplayName("Several time")
    void f13(){
        //given
        String[] times = new String[]{"2015-06-01", "2015-09-01"};
        Settings settings = new Settings("--path", "a", "--from", times[0], times[1]);
        //when
        var time = settings.getFrom();
        //then
        assertThat(time)
            .isEqualTo(OffsetDateTime.of(LocalDate.of(2015, 9, 1), LocalTime.of(0, 0), ZoneOffset.ofHours(0)));
    }
}
