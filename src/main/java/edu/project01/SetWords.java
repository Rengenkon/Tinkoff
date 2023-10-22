package edu.project01;

import java.util.Random;

public class SetWords {
    private static final String[] set = {
        ""
    };

    public static String getWord() {
        Random random = new Random();
        return set[random.nextInt(set.length)];
    }
}
