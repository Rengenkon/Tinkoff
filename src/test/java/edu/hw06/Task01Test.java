package edu.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task01Test {
    @Test
    @DisplayName("Create file")
    void f1() {
        Task01 map = new Task01("src/test/java/edu/hw06/TEST.txt");
    }

    @Test
    @DisplayName("Write to file")
    void f2() {
        Task01 map = new Task01("src/test/java/edu/hw06/TEST.txt");
        map.put("Tttt", "eeeee");
        map.put("SSSSS", "tttt");
    }
}
