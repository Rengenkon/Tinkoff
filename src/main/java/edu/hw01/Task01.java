package edu.hw01;

public class Task01 {

    private static final int SEC_IN_MIN = 60;
    private Task01() {}

    public static int minutesToSeconds(String time) {

        String[] numbers = time.split(":");
        int minutes =  Integer.parseInt(numbers[0]);
        int seconds = Integer.parseInt(numbers[1]);

        if (seconds < 0 || SEC_IN_MIN <= seconds) {
            return -1;
        }
        if (minutes < 0) {
            return -1;
        }

        return minutes * SEC_IN_MIN + seconds;
    }
}
