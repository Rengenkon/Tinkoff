package edu.project01;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class Game {

    private static final int TRY = 5;
    private final PrintStream out;
    private int fail;
    private final Word word;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 5;
    private final Scanner scanner;

    public Game() throws Exception {
        this(SetWords.getWord());
    }

    public Game(String word) throws Exception {
        this(word, System.in, System.out);
    }

    public Game(String word, InputStream in, PrintStream out) throws Exception {
        fail = 0;

        scanner = new Scanner(in);
        this.out = out;

        if (valid(word)) {
            this.word = new Word(word.toLowerCase());
        } else {
            throw new Exception("A short word to start");
        }
    }

    public void start() {
        char c;
        while (true) {
            try {
                c = input();
            }catch (Exception e) {
                out.println(e.getMessage());
                break;
            }

            if (word.inWord(c)) {
                out.println("Hit!");
                word.editMask(c);
            }else {
                fail++;
                out.println(STR."Missed, mistake \{fail} out of 5.");
            }

            out.println(word.getMask());

            if (word.end()) {
                out.println("You won!");
                break;
            } else if (fail == TRY) {
                out.println("You lost!");
                break;
            }
        }
    }

    private char input() throws Exception {
        out.println("Guess a letter:");
        String str = scanner.next();

        if (str.length() == 1 && Character.isLetter(str.charAt(0))) {
            return str.toLowerCase().charAt(0);
        } else if (str.equals("EXIT")) {
            throw new Exception("Game over");
        }else{
            out.println("Incorrect input");
            return input();
        }
    }

    private boolean valid(String word) {
        return word.length() >= MIN_LENGTH  && word.length() <= MAX_LENGTH;
    }
}
