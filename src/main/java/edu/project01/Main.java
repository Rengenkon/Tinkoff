package edu.project01;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        try {
            Game game = new Game("test");
            game.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
