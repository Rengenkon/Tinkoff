package edu.project02;

import edu.project02.generation.LoopMaze;

public class Main {
    public static void main(String[] args) {
        new Display().show(new LoopMaze(5,3).getMaze());
    }
}
