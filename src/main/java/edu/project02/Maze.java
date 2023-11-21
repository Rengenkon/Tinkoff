package edu.project02;

import java.util.Random;

public class Maze {
    public record Point(int height, int weight){}

    protected Random generator;

    protected Point start;
    protected Point end;

    public final int mazeHeight;
    public final int mazeWeight;
    protected int[][] maze;
    public static final int WALL = -1;
    public static final int WAY = -2;
    public static final int MAIN = -3;

    public Maze(int n, int m, long seed) {
        final int minSize = 3;
        generator = new Random(seed);
        mazeHeight = Math.max(n, minSize);
        mazeWeight = Math.max(m, minSize);
        maze = new int[mazeHeight][mazeWeight];
    }

    private Maze(int[][] maze, Point start, Point end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        this.mazeHeight = maze.length;
        this.mazeWeight = mazeHeight > 0 ? maze[0].length : 0;
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

    public Maze setMaze(int[][] maze) {
        return new Maze(maze, this.getStart(), this.getEnd());
    }
}
