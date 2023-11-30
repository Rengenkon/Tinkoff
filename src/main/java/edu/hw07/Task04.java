package edu.hw07;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Task04 {
    private final static int r = 15_000;
    private final static Random random = new Random(System.nanoTime());

    public static double pi(long n, int thread) {
        int thr = Math.max(1, thread);
        AtomicLong count = new AtomicLong();
        ArrayList<Thread> threads = new ArrayList<>(thr);
        for (int i = 0; i < thr; i++) {
            threads.add(new Thread(() -> count.addAndGet(mono(n / thr))));
        }
        threads.forEach(Thread::start);
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return count.get() * 4.0 / (n - n % thr);
    }

    private static long mono(long n) {
        long count = 0;
        for (long j = 0; j < n; j++) {
            int x = random.nextInt(-r, r + 1);
            int y = random.nextInt(-r, r + 1);
            if (x * x + y * y < r * r){
                count++;
            }
        }
        return count;
    }
}
