package edu.hw07;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadLocalRandom;


public class Task04 {
    private final static int RADIUS = 15_000;
//    private final static Random random = new Random(System.nanoTime());

    private Task04() {}

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
        Random random = ThreadLocalRandom.current();
        long count = 0;
        for (long j = 0; j < n; j++) {
            int x = random.nextInt(-RADIUS, RADIUS + 1);
            int y = random.nextInt(-RADIUS, RADIUS + 1);
            if (x * x + y * y < RADIUS * RADIUS) {
                count++;
            }
        }
        return count;
    }
}
