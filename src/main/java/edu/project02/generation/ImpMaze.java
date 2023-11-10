package edu.project02.generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class ImpMaze extends Maze{
    ArrayList<Point> walls = new ArrayList<>();
    private final HashMap<Integer, TreeSet<Point>> map = new HashMap<>();

    public ImpMaze(int n) {
        this(n, n);
    }

    public ImpMaze(int n, int m) {
        this(n, m, System.currentTimeMillis());
    }

    public ImpMaze(int n, int m, long seed) {
        super(n % 2 == 0 ? n - 1 : n, m % 2 == 0 ? m -1 : m, seed);
        generate();
    }

    private void generate() {
        initialization();
        while (!walls.isEmpty()) {
            int index = generator.nextInt(walls.size());
            Point wall = walls.get(index);
            walls.remove(index);
            merge(wall);
        }
        start = new Point(-1, -1);
        end = start;
    }

    private void initialization() {
        int a = 1;
        for (int i = 0; i < mazeHeight; i += 2) {
            for (int j = 0; j < mazeWeight; j += 2) {
                maze[i][j] = a;
                a++;
            }
        }

        for (int i = 0; i < mazeHeight; i++) {
            if (i % 2 == 0){
                for (int j = 1; j < mazeWeight; j += 2) {
                    walls.add(new Point(i, j));
                }
            }else {
                for (int j = 0; j < mazeWeight; j++) {
                    if (j % 2 == 1) {
                        maze[i][j] = WALL;
                    }else {
                        walls.add(new Point(i, j));
                    }
                }
            }
        }
    }

    private void merge(Point point) {
        Point[] p;
        if (point.height() % 2 == 0) {
            p = new Point[]{
                new Point(point.height(), point.weight() - 1),
                new Point(point.height(), point.weight() + 1),
            };
        } else {
            p = new Point[]{
                new Point(point.height() - 1, point.weight()),
                new Point(point.height() + 1, point.weight()),
            };
        }
        int[] values = new int[] {
            maze[p[0].height()][p[0].weight()],
            maze[p[1].height()][p[1].weight()]
        };

        if (values[0] == values[1]) {
            maze[point.height()][point.weight()] = WALL;
        } else {
            ArrayList<TreeSet<Point>> treeSets = new ArrayList<>(2) {{
                add(map.get(values[0]));
                add(map.get(values[1]));
            }};
            int min, max;
            if (treeSets.get(0) != null && treeSets.get(1) != null) {
                min = treeSets.get(0).size() > treeSets.get(1).size() ? 1 : 0;
                max = 1 - min;
                for (Point po : treeSets.get(min)) {
                    maze[po.height()][po.weight()] = values[max];
                }
                maze[point.height()][point.weight()] = values[max];
                treeSets.get(max).addAll(treeSets.get(min));
                treeSets.get(max).add(point);
                map.remove(values[min]);
            } else if (treeSets.get(0) == null && treeSets.get(1) == null) {
                TreeSet<Point> s = new TreeSet<>(new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        if (o1.height() == o2.height()) {
                            return o1.weight() - o2.weight();
                        }
                        return o1.height() - o2.height();
                    }
                });
                max = 0;
                min = 1;
                s.add(p[max]);
                s.add(p[min]);
                s.add(point);
                map.put(values[max], s);
                maze[p[min].height()][p[min].weight()] = values[max];
                maze[point.height()][point.weight()] = values[max];
            } else {
                min = treeSets.get(0) == null ? 0 : 1;
                max = 1 - min;
                treeSets.get(max).add(p[min]);
                treeSets.get(max).add(point);
                maze[p[min].height()][p[min].weight()] = values[max];
                maze[point.height()][point.weight()] = values[max];
            }
        }
    }
}
