package com.app.spacex.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtil {

    private static final String DAY_MONTH_YEAR_DISPLAY_PATTERN = "dd MMMM yyyy";
    private static String TIMEZONE_TR = "GMT+03:00";
    public static String getFormattedCurrentDate(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_TR));
        return simpleDateFormat.format(date);
    }

    public static String getFormattedDayMonthYear(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return getFormattedCurrentDate(DAY_MONTH_YEAR_DISPLAY_PATTERN, calendar.getTime());
    }
}