package edu.project02.generation;

import java.util.ArrayList;
import java.util.Random;

public abstract class Maze {
    protected Random generator;
    protected record Point(int height, int weight){}
    protected Point start;
    protected Point end;
    protected final int mazeHeight;
    protected final int mazeWeight;
    private int[][] ans;

    public static final int START = 10000;
    public static final int END = 20000;
    public static final int WAY = -1;
    public static final int WALL = 1;
    public static final int NONE = 0;

    public Maze(int n, int m){
        this(Math.max(n, 3), Math.max(m, 3), System.currentTimeMillis());
    }

    public Maze(int n, int m, long seed) {
        generator = new Random(seed);
        mazeHeight = n;
        mazeWeight = m;
        generate();
    }

    private void generate() {
        generateStart();
        generateMainWay();
    }

    private void generateStart() {
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
    }

    private void generateMainWay() {
        boolean validValues = false;
        while (!validValues) {
            ans = new int[mazeHeight][mazeWeight];
            ans[start.height][start.weight] = START;
            ans[end.height][end.weight] = END;

            Point ccurrent = start;
            while (true) {
                ccurrent = nextPoint(ccurrent);

                if (ans[ccurrent.height][ccurrent.weight] == END) {
                    validValues = true;
                    break;
                }
                ans[ccurrent.height][ccurrent.weight] = WAY;
            }
        }
    }

    private Point nextPoint(Point x) {
        ArrayList<Point> possible = new ArrayList<>(3);
        Point[] allWays = getWays(x);

        for (var way : allWays) {
            if (end.height == way.height && end.weight == way.weight) {
                return way;
            }else if (way.height <= 0 || way.height >= mazeHeight - 1 || way.weight <= 0 || way.weight >= mazeWeight - 1){
                continue;
            }else {
                int value = ans[way.height][way.weight];
                if (value == WAY) {
                    continue;
                }
                if (value == NONE) {
                    ans[way.height][way.weight] = WALL;
                }
                possible.add(way);
            }
        }

        return possible.get(generator.nextInt(possible.size()));// size == 0
    }

    private Point[] getWays(Point x) {
        Point[] allWays = new Point[]{
            new Point(x.height - 1, x.weight),
            new Point(x.height + 1, x.weight),
            new Point(x.height, x.weight - 1),
            new Point(x.height, x.weight + 1)
        };

        if (Math.abs(x.height - start.height) == mazeHeight - 2) {
            if (end.weight - x.weight < 0){
                allWays = new Point[]{new Point(x.height, x.weight - 1)};
            }else if (end.weight - x.weight > 0){
                allWays = new Point[]{new Point(x.height, x.weight + 1)};
            }
        }else if (Math.abs(x.weight - start.weight) == mazeWeight - 2) {
            if (end.height - x.height < 0){
                allWays = new Point[]{new Point(x.height - 1, x.weight)};
            }else if (end.height - x.height > 0){
                allWays = new Point[]{new Point(x.height + 1, x.weight)};
            }
        }
        return allWays;
    }

    public int[][] getMaze() {
        return ans.clone();
    }

    abstract protected void generateSecondsWay();
}
