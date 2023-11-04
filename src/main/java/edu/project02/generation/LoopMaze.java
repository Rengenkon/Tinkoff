package edu.project02.generation;

import java.util.Random;

public class LoopMaze extends Maze{
    private int[][] lab;

    public LoopMaze(int n, int m){
        super(n, m);
    }

    public LoopMaze(int n, int m, long seed) {
        super(n, m, seed);
        lab = super.getMaze();
    }



    @Override
    protected void generateSecondsWay() {

    }

    @Override
    public int[][] getMaze() {
        return lab;
    }
}
