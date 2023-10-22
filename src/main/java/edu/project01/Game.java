package edu.project01;

import java.util.Scanner;
import static java.lang.StringTemplate.STR;

public class Game {

    private static final int TRY = 5;
    private int fail;
    private final Word word;
    private static final Scanner scanner = new Scanner(System.in);

    public Game() {
        this(SetWords.getWord());
    }

    public Game(String word) {
        this.word = new Word(word);
        fail = 0;
    }

    public void start() {
        char c;
        while (true) {
            try {
                c = input();
            }catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

            if (word.inWord(c)) {
                System.out.println("Hit!");
                word.editMask(c);
            }else {
                fail++;
                System.out.println(STR."Missed, mistake \{fail} out of 5.");
            }

            System.out.println(word.getMask());

            if (word.end()) {
                System.out.println("You won!");
                break;
            } else if (fail == TRY) {
                System.out.println("You lost!");
                break;
            }
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
