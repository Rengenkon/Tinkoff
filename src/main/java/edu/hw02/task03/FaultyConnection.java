package edu.hw02.task03;

import java.util.Random;

public class FaultyConnection implements Connection {
    private final Random random = new Random(System.currentTimeMillis());
    private static final double CHANCE = 1;

    @Override
    public void execute(String command) {
        if (random.nextDouble() <= CHANCE) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
