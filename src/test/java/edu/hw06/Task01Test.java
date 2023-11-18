package edu.hw06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Task01Test {
    @Test
    @DisplayName("Create file")
    void f1() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //when
        Task01 map = new Task01(path);
        //then
        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    @DisplayName("Write and read")
    void f2() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key = "Test", value = "true";
        //when
        map.put(key, value);
        //then
        assertThat(map.get(key)).isEqualTo(value);
    }

    @Test
    @DisplayName("Overwrite")
    void f3() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key = "Test", value1 = "true", value2 = "false";
        //when
        map.put(key, value1);
        map.put(key, value2);
        //then
        assertThat(map.get(key)).isEqualTo(value2);
    }

    @Test
    @DisplayName("Size")
    void f4() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", value = "true", key2 = "test";
        //when
        map.put(key1, value);
        map.put(key2, value);
        //then
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Contains key")
    void f5() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        //then
        assertThat(map.containsKey(key1)).isTrue();
    }
    @Test
    @DisplayName("Not contains key")
    void f6() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        var a = map.containsKey(key2);
        //then
        assertThat(a).isFalse();
    }
    @Test
    @DisplayName("Contains value")
    void f7() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value1);
        //then
        assertThat(map.containsValue(value1)).isTrue();
    }
    @Test
    @DisplayName("Not contains value")
    void f8() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value1);
        //then
        assertThat(map.containsValue(value2)).isFalse();
    }


    @Test
    @DisplayName("Remove return")
    void f9() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value2);
        //then
        assertThat(map.remove(key1)).isEqualTo(value1);
    }

    @Test
    @DisplayName("Remove contain")
    void f10() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value2);
        map.remove(key1);
        //then
        assertThat(map.containsKey(key1)).isFalse();
    }

    @Test
    @DisplayName("Key set")
    void f11() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value2);
        //then
        assertThat(map.keySet().toArray()).hasSize(2).contains(key1, key2);
    }

    @Test
    @DisplayName("Value array")
    void f12() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        //when
        map.put(key1, value1);
        map.put(key2, value2);
        //then
        assertThat(map.values().toArray()).hasSize(2).contains(value1, value2);
    }

    @Test
    @DisplayName("Put all")
    void f13() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        HashMap<String, String> secondMap = new HashMap<>();
        //when
        map.put(key1, value1);
        secondMap.put(key2, value2);
        map.putAll(secondMap);
        //then
        assertThat(map.get(key2)).isEqualTo(value2);
    }

    @Test
    @DisplayName("Put all type exception")
    void f14() {
        //given
        Path path = Paths.get("src/test/java/edu/hw06/TEST.txt");
        Task01 map = new Task01(path);
        map.clear();
        String key1 = "Test", key2 = "test", value1 = "true", value2 = "false";
        HashMap<Integer, Integer> secondMap = new HashMap<>();
        //when
        map.put(key1, value1);
        secondMap.put(1, 0);
        //then
        assertThrows(RuntimeException.class,() -> {
            map.putAll(secondMap);
        });
    }
}
