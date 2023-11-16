import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task01 {
    public static long durationMinut(String string) {
        Matcher matcher = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})").matcher(string);
        if (matcher.find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime enter = LocalDateTime.parse(matcher.group(1), formatter);
            LocalDateTime exit = LocalDateTime.parse(matcher.group(2), formatter);
            Duration dur = Duration.between(enter, exit);
            if (dur.toMinutes() > 0) {
                return dur.toMinutes();
            }
        }
        return 0;
    }

    public static String avarageTime(String[] strings){
        final int minInHour = 60;
        long alltime = 0;
        for (String string : strings) {
            alltime += durationMinut(string);
        }
        double minut = alltime * 1.0 / strings.length;
        int hours = 0;
        while (minut > minInHour) {
            hours++;
            minut -= minInHour;
        }
        return hours + "ч " + (long)minut + "м";
    }

}
