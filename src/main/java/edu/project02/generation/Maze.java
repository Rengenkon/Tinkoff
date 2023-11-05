package edu.project02.generation;

import java.util.Random;

public abstract class Maze {
    protected Random generator;

    protected record Point(int height, int weight){}
    protected Point start;
    protected Point end;

    protected final int mazeHeight;
    protected final int mazeWeight;
    protected int[][] maze;

    public static final int START = 100;
    public static final int END = 101;
    public static final int WAY = 1;
    public static final int SECOND_WAY = 2;
    public static final int WALL = -1;
    public static final int BORDER = -10;
    public static final int NONE = 0;

    public Maze(int n, int m, long seed) {
        generator = new Random(seed);

        if (n % 2 == 0){
            n--;
        }
        if (m % 2 == 0){
            m--;
        }
        mazeHeight = Math.max(n, 3);
        mazeWeight = Math.max(m, 3);
        maze = new int[mazeHeight][mazeWeight];

        generate();
    }

    private void generate() {
        borders();
        startEnd();
    }

    private void borders() {
        for (int i = 1; i < mazeHeight - 1; i++) {
            maze[i][0] = BORDER;
            maze[i][mazeWeight - 1] = BORDER;
        }
        for (int i = 0; i < mazeWeight; i++) {
            maze[0][i] = BORDER;
            maze[mazeHeight - 1][i] = BORDER;
        }
    }

    private void startEnd() {
        start = new Point(-1, -1);
        end = null;
        boolean validValues = false;

        while (!validValues) {
            int h = generator.nextInt(mazeHeight);
            int w;
            if (h == 0 || h == mazeHeight - 1) {
                w = generator.nextInt(1, mazeWeight - 1);
            }else {
                w = generator.nextInt(2) * (mazeWeight - 1);
            }

            if (start.height == -1){
                start = new Point(h, w);
            }else {
                if (start.height == h && start.weight - 1 <= w && start.weight + 1 >= w) {
                    continue;
                }
                if (start.weight == w && start.height - 1 <= h && start.height + 1 >= h) {
                    continue;
                }
                end = new Point(h, w);
                validValues = true;
            }
        }

        maze[start.height][start.weight] = START;
        maze[end.height][end.weight] = END;
    }

    public int[][] getMaze(){
        return maze.clone();
    }
}
