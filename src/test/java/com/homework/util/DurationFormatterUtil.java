package com.homework.util;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Helps to pretty format elapsed duration.
 * Copy-paste of com.google.common.base.Stopwatch
 */
public class DurationFormatterUtil {

    private DurationFormatterUtil() {
    }

    public static String toString(long millis) {
        TimeUnit unit = chooseUnit(millis);
        double value = (double) millis / MILLISECONDS.convert(1, unit);

        return String.format("%.4g %s", value, abbreviate(unit));
    }

    private static TimeUnit chooseUnit(long millis) {
        if (DAYS.convert(millis, MILLISECONDS) > 0) {
            return DAYS;
        }
        if (HOURS.convert(millis, MILLISECONDS) > 0) {
            return HOURS;
        }
        if (MINUTES.convert(millis, MILLISECONDS) > 0) {
            return MINUTES;
        }
        if (SECONDS.convert(millis, MILLISECONDS) > 0) {
            return SECONDS;
        }
        if (MILLISECONDS.convert(millis, MILLISECONDS) > 0) {
            return MILLISECONDS;
        }
        return MILLISECONDS;
    }

    private static String abbreviate(TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return "ns";
            case MICROSECONDS:
                return "\u03bcs"; // μs
            case MILLISECONDS:
                return "ms";
            case SECONDS:
                return "s";
            case MINUTES:
                return "min";
            case HOURS:
                return "h";
            case DAYS:
                return "d";
            default:
                throw new AssertionError();
        }
    }
}
