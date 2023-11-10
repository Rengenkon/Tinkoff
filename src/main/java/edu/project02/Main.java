package edu.project02;

import edu.project02.generation.Kraskal;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        new Display().show(new Kraskal(500));
    }
}
