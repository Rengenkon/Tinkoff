package edu.hw02.task03;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random = new Random(System.currentTimeMillis());
    private static final double CHANCE = 0;

    @Override
    public Connection getConnection() {
        if (random.nextDouble() <= CHANCE) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
