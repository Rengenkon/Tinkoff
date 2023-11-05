package edu.project02;

import java.io.PrintStream;

public class Display {
    PrintStream output;

    public Display(){
        this(System.out);
    }
    public Display(PrintStream out) {
        output = out;
    }

    public void show(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                output.print(maze[i][j] + " ");
            }
            output.println();
        }
    }

}
