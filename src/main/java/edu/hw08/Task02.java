package edu.hw08;

public class Task02 implements AutoCloseable {
    Thread[] threads;

    private Task02(int n) {
        threads = new Thread[n];
    }

    public static Task02 create(int n) {
        if (n < 1) {
            throw new RuntimeException();
        }
        return new Task02(n);
    }

    public void start(){
        if (threads[0] == null) {
            throw new RuntimeException();
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void execute(Runnable runnable){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
        }
    }

    @Override
    public void close() throws Exception {}
}
