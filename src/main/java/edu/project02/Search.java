package edu.project02;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Search {

    private Search() {}

    public static Maze re(Maze m) {
        Queue<Maze.Point> que = new PriorityQueue<>(new Comparator<Maze.Point>() {
            @Override
            public int compare(Maze.Point o1, Maze.Point o2) {
                if (o1.height() == o2.height()) {
                    return o1.weight() - o2.weight();
                }
                return o1.height() - o2.height();
            }
        });
        int[][] maze = m.getMaze();

        var start = start(m.getStart());
        maze[start.height()][start.weight()] = 1;
        que.add(start);
        while (!que.isEmpty()) {
            Maze.Point point = que.poll();
            if (point.equals(m.getEnd())) {
                break;
            }

            int val = Integer.MAX_VALUE;
            for (Maze.Point way : new Maze.Point[]{
                new Maze.Point(point.height() - 1, point.weight()),
                new Maze.Point(point.height() + 1, point.weight()),
                new Maze.Point(point.height(), point.weight() - 1),
                new Maze.Point(point.height(), point.weight() + 1)
            }) {
                if (way.equals(m.getEnd())) {
                    que.clear();
                    que.add(way);
                } else if (way.height() == -1 || way.height() == m.mazeHeight
                    || way.weight() == -1 || way.weight() == m.mazeWeight) {
                    continue;
                } else {
                    int v = maze[way.height()][way.weight()];
                    if (v != Maze.WALL) {
                        if (v == Maze.WAY) {
                            que.add(way);
                        } else {
                            val = Math.min(v, val);
                        }
                    }
                }
                maze[point.height()][point.weight()] = val == Integer.MAX_VALUE ? 1 : val + 1;
            }
        }


        Maze.Point end = start(m.getEnd());
        while (true) {
            int val = maze[end.height()][end.weight()];
            maze[end.height()][end.weight()] = Maze.MAIN;
            if (end.height() == start.height() && end.weight() == start.weight()) {
                break;
            }

            for (Maze.Point way : new Maze.Point[]{
                new Maze.Point(end.height() - 1, end.weight()),
                new Maze.Point(end.height() + 1, end.weight()),
                new Maze.Point(end.height(), end.weight() - 1),
                new Maze.Point(end.height(), end.weight() + 1)
            }) {
                if (way.height() == -1 || way.height() == m.mazeHeight
                    || way.weight() == -1 || way.weight() == m.mazeWeight) {
                    continue;
                } else if (val - 1 == maze[way.height()][way.weight()]) {
                    end = way;
                    break;
                }
            }
        }
        return m.setMaze(maze);
    }

    private static Maze.Point start(Maze.Point start) {
        if (start.height() == -1) {
            return new Maze.Point(0, start.weight());
        } else if (start.weight() == -1) {
            return new Maze.Point(start.height(), 0);
        } else {
            if (start.height() > start.weight()) {
                return new Maze.Point(start.height() - 1, start.weight());
            } else {
                return new Maze.Point(start.height(), start.weight() - 1);
            }
        }
    }
}
