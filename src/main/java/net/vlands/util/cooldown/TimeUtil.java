package net.vlands.util.cooldown;

import java.text.DecimalFormat;

public class TimeUtil {
    public enum TimeUnit {
        BEST,
        DAYS,
        HOURS,
        MINUTES,
        SECONDS,
        MILLISECONDS
    }

    public static Long convert(Long time, TimeUnit unit, int decPoint) {
        if (unit.equals(TimeUnit.BEST)) {
            if (time <= 1000L) unit = TimeUnit.MILLISECONDS;
            else if (time <= 60000L) unit = TimeUnit.SECONDS;
            else if (time <= 3600000L) unit = TimeUnit.MINUTES;
            else if (time <= 86400000L) unit = TimeUnit.HOURS;
            else unit = TimeUnit.DAYS;
        }
        if (unit.equals(TimeUnit.MILLISECONDS)) return trim(time / (1000 / 60), decPoint);
        if (unit.equals(TimeUnit.SECONDS)) return trim(time / 1000, decPoint);
        if (unit.equals(TimeUnit.MINUTES)) return trim(time / 60000, decPoint);
        if (unit.equals(TimeUnit.HOURS)) return trim(time / 3600000, decPoint);
        if (unit.equals(TimeUnit.DAYS)) return trim(time / 86400000, decPoint);
        return trim(time, decPoint);
    }

    private static Long trim(Long untrimmed, int decimal) {
        StringBuilder format = new StringBuilder("#.#");
        for(int i = 1; i < decimal; i++) {
            format.append("#");
        }
        DecimalFormat twoDec = new DecimalFormat(format.toString());
        return Long.parseLong(twoDec.format(untrimmed));
    }

}