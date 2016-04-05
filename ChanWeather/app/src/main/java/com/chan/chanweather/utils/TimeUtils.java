package com.chan.chanweather.utils;

import java.util.Calendar;

/**
 * Created by chan on 16/4/3.
 */
public class TimeUtils {
    public static boolean isDay() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour < 17;
    }
}
