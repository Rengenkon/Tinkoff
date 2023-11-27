package edu.hw07;

import java.util.ArrayList;
import java.util.List;

public class Task01 {
    public static final int TREAD_COUNT = 4;
    final Counter counter = new Counter();

    public int add(int n) {
        List<Thread> threadList = new ArrayList<>(TREAD_COUNT);
        for (int i = 0; i < TREAD_COUNT; i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.add(n);
                }
            }));
        }

        for (Thread thread : threadList) {
            thread.start();
        }
        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return counter.get();
    }
}

class Counter{
    private volatile Integer count = 0;

    public void add(int n) {
        synchronized (count) {
            for (int i = 0; i < n; i++) {
                count++;
            }
        }
    }

    public int get() {
        return count;
    }
}
