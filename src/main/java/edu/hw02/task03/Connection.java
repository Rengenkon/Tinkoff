package edu.hw02.task03;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
