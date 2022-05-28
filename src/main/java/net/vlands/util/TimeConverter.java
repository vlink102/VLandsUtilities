package net.vlands.util;

import net.vlands.util.math.MathUtils;

import java.util.List;

public class TimeConverter {
    public enum TimeUnit {
        BEST,
        SECONDS,
        MINUTES,
        HOURS,
        DAYS,
        MONTHS,
        YEARS,
        DECADES,
        CENTURIES,
        MILLENNIA
    }

    public static String format(long unixTime, TimeUnit timeUnit) {
        String time = "";

        int seconds = (int) (unixTime / 1000) % 60;
        int minutes = (int) ((unixTime / (1000 * 60)) % 60);
        int hours = (int) ((unixTime / (1000 * 60 * 60)) % 24);
        int days = (int) ((unixTime / (1000 * 60 * 60 * 24)));

        if (days != 0) time = days + " day" + (days == 1 ? "" : "s");
        if (hours != 0) time = time + (time.equals("") ? "" : " ") + hours + " hour" + (hours == 1 ? "" : "s");
        if (minutes != 0) time = time + (time.equals("") ? "" : " ") + minutes + " minute" + (minutes == 1 ? "" : "s");
        if (seconds != 0) time = time + (time.equals("") ? "" : " ") + seconds + " second" + (seconds == 1 ? "" : "s");
        return time;
    }
}
