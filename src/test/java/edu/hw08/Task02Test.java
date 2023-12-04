package edu.hw08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task02Test {
    @Test
    @DisplayName("Фибоначи")
    void f() {
        //given
        Task02 task = Task02.create(1);
        ExecutorService s = Executors.newFixedThreadPool(1);
        //when
        task.execute(new Fib(1));
        task.execute(new Fib(10));
        s.execute(new Fib(1));
        s.execute(new Fib(10));
        //then
        task.start();
    }
}
