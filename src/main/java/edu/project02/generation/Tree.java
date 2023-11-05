package edu.project02.generation;

import java.util.TreeSet;

public class Tree extends Maze{
    public Tree(int n, int m) {
        this(n, m, System.currentTimeMillis());
    }

    public Tree(int n, int m, long seed) {
        super(n, m, seed);
    }

    private void generate() {
        generator.nextInt(1, mazeHeight);
        TreeSet <Point> set = new TreeSet<>();

        set.
    }
}
