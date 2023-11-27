package edu.hw07;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Task04 {
    public static double pi(long n) {
        final int THREAD = 4;
        final int r = 15_000;
        final Random random = new Random(System.nanoTime());
        AtomicLong count = new AtomicLong();
        ArrayList<Thread> threads = new ArrayList<>(THREAD);
        for (int i = 0; i < THREAD; i++) {
            threads.add(new Thread(() -> {
                for (long j = 0; j < n / THREAD; j++) {
                    int x = random.nextInt(-r, r + 1);
                    int y = random.nextInt(-r, r + 1);
                    if (x * x + y * y < r * r){
                        count.getAndIncrement();
                    }
                }
            }));
        }
        threads.forEach(Thread::start);
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count.get() * 4.0 / (n - n % THREAD);
    }
}
