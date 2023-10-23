package edu.project01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ATest {
    private final InputStream modIn = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    private final PrintStream modOut = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) throws IOException {

        }
    });

    @Test
    @DisplayName("")
    void nul (){
        //given
        try {
            Game game = new Game("", modIn, modOut);
            game.start();
        }catch (Exception e){
            modOut.println(e.getMessage());
        }
        //when
        //then
    }
}
