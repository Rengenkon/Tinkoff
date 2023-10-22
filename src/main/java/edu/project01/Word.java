package edu.project01;

import java.util.Arrays;

public class Word {
    private final char[] word;
    private char[] mask;

    public Word(String word) {
        this.word = word.toCharArray();
        mask = "*".repeat(word.length()).toCharArray();
    }

    public char[] getMask() {
        return mask;
    }

    public void editMask(char c) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] == c) {
                mask[i] = c;
            }
        }
    }

    public boolean inWord(char a) {
        for (char c: word) {
            if (c == a) {
                return true;
            }
        }
        return false;
    }

    public boolean end() {
        return Arrays.equals(word, mask);
    }
}
