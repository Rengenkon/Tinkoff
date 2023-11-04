package edu.project02.generation;

public class LoopMaze extends Maze{
    private int[][] lab;

    public LoopMaze(int n, int m){
        super(n, m);
    }

    public LoopMaze(int n, int m, long seed) {
        super(n, m, seed);
        lab = super.getMaze();
        generateSecondsWay();
    }



    @Override
    protected void generateSecondsWay() {
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

    private Point nextPoint(Point x) {
        return x;
    }

    @Override
    public int[][] getMaze() {
        return lab;
    }
}
