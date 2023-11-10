package edu.project02;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        final int size = 10;
        new Display().show(Search.re(new Kraskal(size)));
    }
}
