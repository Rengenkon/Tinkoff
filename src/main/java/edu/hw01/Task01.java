package edu.hw01;

public class Task01 {
    private Task01(){}

    public static int minutesToSeconds(String time){
        String[] numbers = time.split(":");
        int minutes =  Integer.parseInt(numbers[0]),
            seconds = Integer.parseInt(numbers[1]);

        if (seconds < 0 || 60 <= seconds) return -1;
        if (minutes < 0) return -1;
        return minutes * 60 + seconds;
    }
}
