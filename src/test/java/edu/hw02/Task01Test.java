package edu.hw02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task01Test {
    @Test
    @DisplayName("Number")
    public void f1() {
        //given
        double d = -1;
        //when
        var c  = new Task01.Constant(d).evaluate();
        //then
        assertThat(c).isEqualTo(d);
    }

    @Test
    @DisplayName("Negative")
    public void f2() {
        //given
        double d = -1;
        //when
        var c  = new Task01.Negate(new Task01.Constant(d))
            .evaluate();
        //then
        assertThat(c).isEqualTo(-1 * d);
    }

    @Test
    @DisplayName("Pow")
    public void f3() {
        //given
        double d = 2;
        //when
        var c  = new Task01.Exponent(new Task01.Constant(d), 3)
            .evaluate();
        //then
        assertThat(c).isEqualTo(8);
    }

    @Test
    @DisplayName("Add")
    public void f4() {
        //given
        double d = 2;
        double f = -2;
        //when
        var c  = new Task01.Addition(
            new Task01.Constant(d),
            new Task01.Constant(f)
        ).evaluate();
        //then
        assertThat(c).isEqualTo(0);
    }

    @Test
    @DisplayName("Mult")
    public void f5() {
        //given
        double d = 2;
        double f = -2;
        //when
        var c  = new Task01.Multiplication(
            new Task01.Constant(d),
            new Task01.Constant(f)
        ).evaluate();
        //then
        assertThat(c).isEqualTo(-4);
    }
}
