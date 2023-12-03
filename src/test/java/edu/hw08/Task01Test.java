package edu.hw08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task01Test {
    @Test
    @DisplayName("Фибоначи")
    void f() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Task02 task = Task02.create(4);
        task.execute(runnable);
        //when
        task.start();
        //then

    }

}
