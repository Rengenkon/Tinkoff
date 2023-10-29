package edu.hw03;

import java.util.HashMap;

public class Task03<T> {
    public HashMap<T, Integer> freqDict(T[] arr) {
        HashMap<T, Integer> thesaurus = new HashMap<T, Integer>();

        for (T s: arr) {
            if (thesaurus.containsKey(s)) {
                thesaurus.put(s, thesaurus.get(s) + 1);
            } else {
                thesaurus.put(s, 1);
            }
        }

        return thesaurus;
    }
}
