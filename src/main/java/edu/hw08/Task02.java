package edu.hw08;

public class Task02 implements AutoCloseable {
    private static final int INIT_SIZE = 2;
    Thread[] run;
    Runnable[] queue;
    int nextRun = 0;
    int nextInsert = 0;

    boolean rewrite = false;

    private Task02(int n) {
        run = new Thread[n];
    }

    public static Task02 create(int n) {
        if (n < 1) {
            throw new RuntimeException();
        }
        return new Task02(n);
    }

    public void start(){
        while (true) {
            int unruned = 0;
            for (int i = 0; i < run.length; i++) {
                if (run[i] == null) {
                    if (nextRun < nextInsert || (rewrite && nextRun >= nextInsert)){
                        run[i] = new Thread(queue[nextRun]);
                        nextRun = increment(nextRun);
                        run[i].start();
                    } else {
                        unruned++;
                    }
                } else if (!run[i].isAlive()) {
                    run[i] = null;
                    i--;
                }
            }

            if (unruned == run.length) {
                break;
            }
        }
    }

    private int increment(int i) {
        int j = i + 1;
        if (j == queue.length) {
            if (rewrite) {
                rewrite = false;
            }else {
                rewrite = true;////////////////////////////////////////////////////////////////////
            }
            j = 0;
        }
        return j;
    }

    public void execute(Runnable runnable){
        if (runnable != null) {
            if (queue == null) {
                queue = new Runnable[INIT_SIZE];
            }

            if (nextInsert == queue.length) {
                if (nextRun == 0) {
                    Runnable[] newQueue = new Runnable[queue.length * 2];
                    for (int i = nextRun; i < nextInsert; i++) {
                        newQueue[i] = queue[i];
                    }
                    queue = newQueue;
                }
            }else if (rewrite && nextInsert == nextRun){
                rewrite = false;
                Runnable[] newQueue = new Runnable[queue.length * 2];
                for (int i = nextRun; i < queue.length; i++) {
                    newQueue[i - nextRun] = queue[i];
                }
                for (int i = 0; i < nextInsert; i++) {
                    newQueue[i + queue.length - nextRun] = queue[i];
                }
                nextRun = 0;
                nextInsert = queue.length;
                queue = newQueue;
            }

            queue[nextInsert] = runnable;
            nextInsert = increment(nextInsert);
        }
    }

    @Override
    public void close() throws Exception {}
}



class Fib implements Runnable {
    int n;

    public Fib(int n){
        this.n = n;
    }

    @Override
    public void run() {
        int n1 = 1;
        int n2 = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(" " + n1);
            int t = n2;
            n2 += n1;
            n1 = t;
        }
        System.out.println();
    }
}
