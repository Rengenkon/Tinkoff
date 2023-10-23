package edu.project01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ATest {
    private final LinkedList<String> input = new LinkedList<String>();
    private final StringBuilder builder= new StringBuilder();

    private final BufferedReader bt = new BufferedReader(new InputStreamReader(System.in)){
        @Override
        public String readLine() throws IOException {
            return input.pop();
        }
    };

    private final PrintStream modOut = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {
            builder.append((char) b);
        }
    });

    @Test
    @DisplayName("Easy win")
    void win1 (){
        //given
        input.add("t");
        input.add("e");
        input.add("s");

        String rightOut =
            "Guess a letter:\n" +
            "Hit!\n" +
            "t**t\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "te*t\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "test\n" +
            "You won!\n";
        //when
        try {
            Game game = new Game("test", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }

        String out = builder.toString();
        //then
        assertThat(out)
            .isEqualTo(rightOut);
    }
    @Test
    @DisplayName("Wrong word")
    void notValid (){
        //given
        input.add("t");
        input.add("e");
        input.add("s");

        String rightOut =
            "Inappropriate word length\n";
        //when
        try {
            Game game = new Game("testdsadsadsad", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }

        String out = builder.toString();
        //then
        assertThat(out)
            .isEqualTo(rightOut);
    }

    @Test
    @DisplayName("5 trys")
    void lose (){
        //given
        input.add("a");
        input.add("b");
        input.add("c");
        input.add("d");
        input.add("e");
        input.add("f");

        String rightOut =
            "Guess a letter:\n" +
            "Missed, mistake 1 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Missed, mistake 2 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Missed, mistake 3 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Missed, mistake 4 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "*e**\n" +
            "Guess a letter:\n" +
            "Missed, mistake 5 out of 5.\n" +
            "*e**\n" +
            "You lost!\n";
        //when
        try {
            Game game = new Game("test", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }

        String out = builder.toString();
        //then
        assertThat(out)
            .isEqualTo(rightOut);
    }

    @Test
    @DisplayName("Misspell")
    void miss (){
        //given
        input.add("a");
        input.add("b");
        input.add("tt");
        input.add("t");
        input.add("S");
        input.add("Ee");
        input.add("e");

        String rightOut =
            "Guess a letter:\n" +
            "Missed, mistake 1 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Missed, mistake 2 out of 5.\n" +
            "****\n" +
            "Guess a letter:\n" +
            "Incorrect input\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "t**t\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "t*st\n" +
            "Guess a letter:\n" +
            "Incorrect input\n" +
            "Guess a letter:\n" +
            "Hit!\n" +
            "test\n" +
            "You won!\n";
        //when
        try {
            Game game = new Game("test", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }

        String out = builder.toString();
        //then
        assertThat(out)
            .isEqualTo(rightOut);
    }

    @Test
    @DisplayName("EXIT")
    void exit (){
        //given
        input.add("a");
        input.add("b");
        input.add("c");
        input.add("EXIT");
        input.add("e");
        input.add("f");

        String rightOut =
            "Guess a letter:\n" +
                "Missed, mistake 1 out of 5.\n" +
                "****\n" +
                "Guess a letter:\n" +
                "Missed, mistake 2 out of 5.\n" +
                "****\n" +
                "Guess a letter:\n" +
                "Missed, mistake 3 out of 5.\n" +
                "****\n" +
                "Guess a letter:\n" +
                "Game over\n";
        //when
        try {
            Game game = new Game("test", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }

        String out = builder.toString();
        //then
        assertThat(out)
            .isEqualTo(rightOut);
    }
}
