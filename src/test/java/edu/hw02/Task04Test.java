package edu.hw02;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task04Test {
    @Test
    public void f1() {
        //given
        Task04.CallingInfo right = new Task04.CallingInfo(this.getClass().getName(), "f1");
        //when
        Task04.CallingInfo call = Task04.getCalling();
        //then
        assertThat(call).isEqualTo(right);
    }
}
