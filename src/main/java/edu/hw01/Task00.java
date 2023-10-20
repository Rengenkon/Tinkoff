package edu.hw01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task00 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task00() {}

    public static void sayHello() {
        LOGGER.info("Привет, мир!");
    }
}
