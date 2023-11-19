package edu.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

public class Task02Test {
    @Test
    @DisplayName("File not exist")
    void f1() {
        Task02.cloneFile(Paths.get("src/test/java/edu/hw06/TEST.txt"));
    }
}
