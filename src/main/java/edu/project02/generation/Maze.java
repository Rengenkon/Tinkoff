package edu.project02.generation;

import java.util.Random;

public abstract class Maze {
    public record Point(int height, int weight){}

    protected Random generator;

    protected Point start;
    protected Point end;

    public final int mazeHeight;
    public final int mazeWeight;
    protected int[][] maze;
    public static final int WALL = -1;

    public Maze(int n, int m, long seed) {
        final int minSize = 3;
        generator = new Random(seed);
        mazeHeight = Math.max(n, minSize);
        mazeWeight = Math.max(m, minSize);
        maze = new int[mazeHeight][mazeWeight];
    }

    public int[][] getMaze() {
        return maze.clone();
    }

    public Point getEnd() {
        return end;
    }

    public Point getStart() {
        return start;
    }
}
