package org.example.framework;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class TimeUtilities {

    private static final String ISO_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private static String formatDateTime(ZonedDateTime zonedDateTime, String strFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strFormatter);
        return zonedDateTime.format(formatter);
    }

    public static String currentIso() {
        return formatDateTime(ZonedDateTime.now(), ISO_FORMATTER);
    }

    public static String pastIso(int amount, TemporalUnit timeUnit) {
        return formatDateTime(ZonedDateTime.now().minus(amount, timeUnit), ISO_FORMATTER);
    }

    public static String futureIso(int amount, TemporalUnit timeUnit) {
        return formatDateTime(ZonedDateTime.now().plus(amount, timeUnit), ISO_FORMATTER);
    }

    public static String getUtcTimeFrom(String iso) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(iso, DateTimeFormatter.ofPattern(ISO_FORMATTER));
        ZonedDateTime utcTime = zonedDateTime.withZoneSameInstant(ZoneOffset.UTC);
        return formatDateTime(utcTime, ISO_FORMATTER);
    }

}
