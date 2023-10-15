package edu.hw01;

public class Task08 {
    private static final int FIELD_SIZE = 8;
    private Task08() {}

    public static boolean knightBoardCapture(int[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == 1) {
                    int[][] validMoves = validMoves(i, j);
                    for (int[] move : validMoves) {
                        if (move[0] != -1 && field[move[0]][move[1]] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private static int[][] validMoves(int i, int j) {
        int[][] moves = new int[][] {
            {i + 2, j + 1},
            {i + 2, j - 1},
            {i - 2, j + 1},
            {i - 2, j - 1},
            {i + 1, j + 2},
            {i + 1, j - 2},
            {i - 1, j + 2},
            {i - 1, j - 2}
        };

        for (int[] move : moves) {
            if (move[0] < 0 || move[0] > FIELD_SIZE - 1
                || move[1] < 0 || move[1] > FIELD_SIZE - 1) {
                move[0] = -1;
                move[1] = -1;
            }
        }
        return moves;
    }
}
