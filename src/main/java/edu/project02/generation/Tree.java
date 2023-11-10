package edu.project02.generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Tree extends Maze{
    public Tree(int n, int m) {
        this(n, m, System.currentTimeMillis());
    }

    public Tree(int n, int m, long seed) {
        super(n % 2 == 0 ? n - 1 : n, m % 2 == 0 ? m -1 : m, seed);
        generate();
        start = new Point(-1, -1);
        end = start;
    }

    private void generate() {
        int a = 1;
        for (int i = 0; i < mazeHeight; i += 2) {
            for (int j = 0; j < mazeWeight; j += 2) {
                maze[i][j] = a;
                a++;
            }
        }

        Point[][] points = new Point[mazeHeight][ mazeWeight];
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWeight; j++) {
                points[i][j] = new Point(i, j);
            }
        }

        ArrayList<Point> walls = new ArrayList<>();
        for (int i = 0; i < mazeHeight; i++) {
            if (i % 2 == 0){
                for (int j = 1; j < mazeWeight; j += 2) {
                    walls.add(points[i][j]);
                }
            }else {
                for (int j = 0; j < mazeWeight; j++) {
                    if (j % 2 == 1) {
                        maze[i][j] = WALL;
                    }else {
                        walls.add(points[i][j]);
                    }
                }
            }
        }

        while (!walls.isEmpty()) {
            int index = generator.nextInt(walls.size());
            Point wall = walls.get(index);
            walls.remove(index);
            int pHeight = wall.height();
            int pWeight = wall.weight();

            if (pHeight % 2 == 0) {
                if (maze[pHeight][pWeight - 1] != maze[pHeight][pWeight + 1]) {
                    maze[pHeight][pWeight] = merge(points[pHeight][pWeight - 1], points[pHeight][pWeight + 1]);
                }
            }else {
                if (maze[pHeight - 1][pWeight] != maze[pHeight + 1][pWeight]) {
                    maze[pHeight][pWeight] = merge(points[pHeight - 1][pWeight], points[pHeight + 1][pWeight]);
                }
            }
        }
    }

    ArrayList<TreeSet<Point>> listTree = new ArrayList<>();


    private int merge(Point p1, Point p2) {
        int value;
        int s1 = -1;
        int s2 = -1;
        for (int i = 0; i < listTree.size(); i++) {
            if (listTree.get(i).contains(p1)) {
                s1 = i;
            }else if (listTree.get(i).contains(p2)) {
                s2 = i;
            }
        }

        if (s1 != -1 && s2 != -1) {
            TreeSet <Point> set1 = listTree.get(s1);
            TreeSet <Point> set2 = listTree.get(s2);
            value = maze[p1.height()][p1.weight()];

            if(set1.size() > set2.size()) {
                for (Point point : set2){
                    maze[point.height()][point.weight()] = value;
                }
                set1.addAll(set2);

                listTree.remove(s2);
            }else {
                for (Point point : set1){
                    maze[point.height()][point.weight()] = value;
                }
                set2.addAll(set1);

                listTree.remove(s1);
            }
        } else if (s1 == -1 && s2 == -1) {
            TreeSet<Point> s = new TreeSet<>(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    if (o1.height() == o2.height()) {
                        return o1.weight() - o2.weight();
                    }
                    return o1.height() - o2.height();
                }
            });
            s.add(p1);
            s.add(p2);
            value = maze[p1.height()][p1.weight()];
            maze[p2.height()][p2.weight()] = value;
            listTree.add(s);
        }else if (s1 == -1) {
            listTree.get(s2).add(p1);
            value = maze[p2.height()][p2.weight()];
            maze[p1.height()][p1.weight()] = value;
        }else {
            listTree.get(s1).add(p2);
            value = maze[p1.height()][p1.weight()];
            maze[p2.height()][p2.weight()] = value;
        }
        return value;
    }
}
