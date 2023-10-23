package edu.project01;

import java.util.Random;

public class SetWords {
    private static final String[] SET_WORDS = {
        "test",
        "Mouse"
    };

    public static String getWord() {
        Random random = new Random(System.currentTimeMillis());
        return SET_WORDS[random.nextInt(SET_WORDS.length)];
    }
}
