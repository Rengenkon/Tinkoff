import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Task01Test {
    @Test
    @DisplayName("Valid")
    public void f1(){
        //given
        String label = "2022-03-12, 20:20 - 2022-03-12, 23:50.";
        //when
        long duration = Task01.durationMinut(label);
        //then
        assertThat(duration).isEqualTo(210);
    }

    @Test
    @DisplayName("Day")
    public void f2(){
        //given
        String label = "2022-03-12, 20:20 - 2022-03-13, 20:20.";
        //when
        long duration = Task01.durationMinut(label);
        //then
        assertThat(duration).isEqualTo(24 * 60);
    }

    @Test
    @DisplayName("Negative value")
    public void f3(){
        //given
        String label = "2022-03-12, 23:50 - 2022-03-12, 20:20.";
        //when
        long duration = Task01.durationMinut(label);
        //then
        assertThat(duration).isEqualTo(0);
    }

    @Test
    @DisplayName("One label")
    public void f4(){
        //given
        String[] label = new String[]{
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        };
        //when
        String avarage = Task01.avarageTime(label);
        //then
        assertThat(avarage).isEqualTo("3ч 30м");
    }

    @Test
    @DisplayName("Valid values")
    public void f5(){
        //given
        String[] label = new String[]{
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 00:20 - 2022-03-13, 23:50"
        };
        //when
        String avarage = Task01.avarageTime(label);
        //then
        assertThat(avarage).isEqualTo("25ч 30м");
    }

    @Test
    @DisplayName("Not int min")
    public void f6(){
        //given
        String[] label = new String[]{
            "2022-03-12, 20:20 - 2022-03-12, 20:21",
            "2022-03-12, 20:20 - 2022-03-12, 20:22"
        };
        //when
        String avarage = Task01.avarageTime(label);
        //then
        assertThat(avarage).isEqualTo("0ч 1м");
    }
}
