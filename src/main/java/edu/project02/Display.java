package edu.project02;

import java.io.PrintStream;
import edu.project02.generation.Maze;

public class Display {
    private static final char WALL = '*';
    private static final char WAY = '-';
    PrintStream output;

    public Display(){
        this(System.out);
    }
    public Display(PrintStream out) {
        output = out;
    }

    public void show(Maze mmaze) {
        Maze.Point end = mmaze.getEnd();
        Maze.Point start = mmaze.getStart();
        int[][] maze = mmaze.getMaze();

        for (int i = 0; i < maze.length + 2; i++) {
            if (i == 0 || i == maze.length + 1) {
                for (int j = 0; j < maze.length + 2; j++) {
                    output.print(WALL);
                }
                output.println();
            }else {
                for (int j = 0; j < maze[i].length + 2; j++) {
                    if (i == start.height() && j == start.weight()) {
                        output.print(WAY);
                    }else if (i == end.height() && j == end.weight()) {
                        output.print(WAY);
                    }else if (j == 0 || j == maze.length + 1) {
                        output.print(WALL);
                    }else {
                        if (maze[i][j] == Maze.BORDER || maze[i][j] == Maze.WALL) {
                            output.print(WALL);
                        }else {
                            output.print(maze[i][j]);
                        }
                    }
                }
                output.println();
            }
        }
    }

}
