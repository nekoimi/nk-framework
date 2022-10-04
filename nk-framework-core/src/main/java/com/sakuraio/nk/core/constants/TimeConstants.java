package com.sakuraio.nk.core.constants;

import java.time.format.DateTimeFormatter;

/**
 * <p>TimeConstants</p>
 *
 * @author nekoimi 2022/10/04
 */
public final class TimeConstants {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private TimeConstants() {
    }
}
