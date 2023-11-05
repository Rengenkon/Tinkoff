package edu.project02.generation;

import java.util.ArrayList;

public class LoopMaze extends Maze{
    private static final double CHANCE_WAY = 0.5;

    public LoopMaze(int n, int m){
        this(n, m, System.currentTimeMillis());
    }

    public LoopMaze(int n, int m, long seed) {
        super(n, m, seed);

    }

    private void generateMainWay() {
        boolean validValues = false;
        while (!validValues) {
            maze = new int[mazeHeight][mazeWeight];
            maze[start.height()][start.weight()] = START;
            maze[end.height()][end.weight()] = END;

            Point ccurrent = start;
            while (true) {
                ccurrent = nextPoint(ccurrent);

                if (maze[ccurrent.height()][ccurrent.weight()] == END) {
                    validValues = true;
                    break;
                }
                maze[ccurrent.height()][ccurrent.weight()] = WAY;
            }
        }
    }

    private Point nextPoint(Point x) {
        ArrayList<Point> possible = new ArrayList<>(3);
        Point[] points = getPoints(x);

        for (var way : points) {
            if (end.height() == way.height() && end.weight() == way.weight()) {
                return way;
            }else if (onBoard(way)) {
                int value = maze[way.height()][way.weight()];
                if (value == WAY) {
                    continue;
                }
                if (value == NONE) {
                    maze[way.height()][way.weight()] = WALL;
                }
                possible.add(way);
            }
        }
        return possible.get(generator.nextInt(possible.size()));// size == 0
    }

    private Point[] getPoints(Point x) {
        Point[] all = new Point[]{
            new Point(x.height() - 1, x.weight()),
            new Point(x.height() + 1, x.weight()),
            new Point(x.height(), x.weight() - 1),
            new Point(x.height(), x.weight() + 1)
        };

        if (Math.abs(x.height() - start.height()) == mazeHeight - 2) {
            if (end.weight() - x.weight() < 0){
                all = new Point[]{new Point(x.height(), x.weight() - 1)};
            }else if (end.weight() - x.weight() > 0){
                all = new Point[]{new Point(x.height(), x.weight() + 1)};
            }
        }else if (Math.abs(x.weight() - start.weight()) == mazeWeight - 2) {
            if (end.height() - x.height() < 0){
                all = new Point[]{new Point(x.height() - 1, x.weight())};
            }else if (end.height() - x.height() > 0){
                all = new Point[]{new Point(x.height() + 1, x.weight())};
            }
        }
        return all;
    }

    protected void generatesWays() {
        Point ccurrent = start;
        while (true) {
            ccurrent = nextPoint(ccurrent);

            Point[] points = valid(ccurrent);

            for (Point point : points) {
                if (super.generator.nextDouble() < CHANCE_WAY) {
                    maze[point.height()][point.weight()] = SECOND_WAY;
                }
            }
        }
    }

    private Point[] valid(Point x) {
        ArrayList<Point> possible = new ArrayList<>(3);

        Point[] all = new Point[]{
            new Point(x.height() - 1, x.weight()),
            new Point(x.height() + 1, x.weight()),
            new Point(x.height(), x.weight() - 1),
            new Point(x.height(), x.weight() + 1)
        };

        for (int i = 0; i < all.length; i++) {
            int count = 0;
            for (Point adjacent : new Point[]{
                new Point(all[i].height() - 1, all[i].weight()),
                new Point(all[i].height() + 1, all[i].weight()),
                new Point(all[i].height(), all[i].weight() - 1),
                new Point(all[i].height(), all[i].weight() + 1)
            }) {
                if (maze[adjacent.height()][adjacent.weight()] == WAY || maze[adjacent.height()][adjacent.weight()] == SECOND_WAY) {
                    count++;
                }
            }
            if (count > 1) {
                all[i] = null;
            }
        }
        return all;
    }

    private boolean onBoard(Point x){
        return x.height() > 0 && x.height() < mazeHeight - 1 ||
            x.weight() > 0 && x.weight() < mazeWeight - 1;
    }
}
