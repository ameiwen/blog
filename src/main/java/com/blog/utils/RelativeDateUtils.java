package com.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelativeDateUtils {
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_YESTODAY = "昨天";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static void main(String[] args) throws ParseException {
        Date time = new Date(1512805952000L);
        String nowStr = format(time);
        System.out.println(nowStr);
    }

    public static String format(Date date) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:m:s");

        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return ONE_YESTODAY + hourFormat.format(date);
        }
        if (delta < 30L * ONE_DAY) {
            return timeFormat.format(date);
        }
        if (delta < 12L * 4L * ONE_WEEK) {
           return timeFormat.format(date);
        } else {
            return timeFormat.format(date);
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
