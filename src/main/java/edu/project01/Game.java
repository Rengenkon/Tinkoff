package edu.project01;

import java.util.Scanner;

public class Game {

    private static final int TRYS = 5;
    private static int usedTrys;
    private static String HIDDEM_WORD;
    private static final Scanner scanner = new Scanner(System.in);

    public Game() {
        this("random from pull");
    }

    public Game(String word) {
        HIDDEM_WORD = word;

        usedTrys = 0;
    }

    public static void start() {
        while (usedTrys != TRYS) {
            char c;
            try {
                c = input();
            }catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            usedTrys++;

            if (HIDDEM_WORD)
        }
    }

    private static char input() throws Exception {
        System.out.println("Guess a letter:");
        String str = scanner.next();

        if (str.length() == 1 && Character.isLetter(str.charAt(0))) {
            return str.toLowerCase().charAt(0);
        } else if (str.equals("EXIT")) {
            throw new Exception("Game over");
        }else{
            input();
        }
        throw new Exception("atypical input".toUpperCase());
    }
}
