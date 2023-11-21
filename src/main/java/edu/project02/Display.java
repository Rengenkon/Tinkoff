package edu.project02;

import java.io.PrintStream;

public class Display {
    private static final char WALL = '0';
    private static final char WAY = '.';
    private static final char MAIN = '*';
    private static final char START = '+';
    private static final char FINISH = '-';
    PrintStream output;

    public Display() {
        this(System.out);
    }

    public Display(PrintStream out) {
        output = out;
    }

    public void show(Maze mmaze) {
        Maze.Point end = mmaze.getEnd();
        Maze.Point start = mmaze.getStart();
        int[][] maze = mmaze.getMaze();

        for (int i = -1; i < mmaze.mazeHeight + 1; i++) {
            for (int j = -1; j < mmaze.mazeWeight + 1; j++) {
                if (i == start.height() && j == start.weight()) {
                    output.print(START);
                } else if (i == end.height() && j == end.weight()) {
                    output.print(FINISH);
                } else if (i == -1 || j == -1 || i == maze.length || j == maze.length) {
                    output.print(WALL);
                } else {
                    if (maze[i][j] == Maze.WALL) {
                        output.print(WALL);
                    } else if (maze[i][j] == Maze.MAIN) {
                        output.print(MAIN);
                    } else {
                        output.print(WAY);
                    }
                }
            }
            output.println();
        }
    }

}
