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
    LinkedList<String> input = new LinkedList<String>();
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
    @DisplayName("")
    void nul (){
        //given
        input.add("t");
        input.add("e");
        input.add("s");

        try {
            Game game = new Game("test", bt, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }
        //when
        String out = builder.toString();
        System.out.println(out);
        //then
    }
}
