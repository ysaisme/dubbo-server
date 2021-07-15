package com.ysa.util;

import com.ysa.enums.DatePatternEnum;
import com.ysa.expection.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ysa.enums.DatePatternEnum.NORM_DATETIME_PATTERN;


public class DateUtil {

    public final static String FORMAT_YYMMDD = "yyMMdd";
    public final static String FORMAT_YYYY_MM = "yyyy-MM";
    public static final DateTimeFormatter YYYY_MM_DDHHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final static String FORMAT_YYYYMMDD = "yyyy年MM月dd日";
    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_MMDD = "MM月dd日";
    public final static String FORMAT_MMDDHH = "M月dd日HH时";

    /**
     * GMT ZoneId
     */
    private static final ZoneId GMT_ZONE_ID = ZoneId.of("GMT");

    /**
     * 把日期字符串格式化成日期类型
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convert2Date(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.setLenient(false);
            return simple.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 把日期类型格式化成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String convert2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转sql的time格式
     *
     * @param date
     * @return
     */
    public static java.sql.Timestamp convertSqlTime(Date date) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 转sql的日期格式
     *
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDate(Date date) {
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }


    /**
     * 获取当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取日期的时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取日期的分种
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 获取哪一年共有多少周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天是一年中的多少周 如果当前周跨年则属于后一年
     *
     * @param date
     * @return
     */
    public static int getWeekNumOfYearLeanBack(Date date) {
        Calendar c = Calendar.getInstance();
//        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }

    /**
     * 得到某年某周的最后一天 (例如2018-12-31是周一 2019-01-06是周日， 此方法会认为这一周是2019年的第一周)
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeekLeanBack(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的第一天 (例如2018-12-31是周一 2019-01-06是周日， 此方法会认为这一周是2019年的第一周)
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeekLeanBack(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 计算某一年某一月的第一天
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 计算某一年某一月的最后
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }


    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    /**
     * 增加秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }


    /**
     * time差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffTimes(Date before, Date after) {
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffSecond(Date before, Date after) {
        return (after.getTime() - before.getTime()) / 1000;
    }

    /**
     * 分种差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMinute(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60;
    }

    /**
     * 时差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffHour(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 天数差
     *
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    /**
     * 月差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after) {
        int monthAll = 0;
        int yearsX = diffYear(before, after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll = yearsX * 12 + monthsX;
        int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if (daysX > 0) {
            monthAll = monthAll + 1;
        }
        return monthAll;
    }

    /**
     * 年差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    /**
     * 设置23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当天的零点时间 00:00:00
     */
    public static Date getStartOfDay(Date date) {
        return getStartOfDay(date, 0);
    }

    /**
     * 获取N天后的零点时间 00:00:00
     */
    public static Date getStartOfDay(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * get current unix time
     *
     * @return return current unix time
     */
    public static int nowUnix() {
        return (int) Instant.now().getEpochSecond();
    }

    /**
     * format unix time to string
     *
     * @param unixTime unix time
     * @param pattern  date format pattern
     * @return return string date
     */
    public static String toString(long unixTime, String pattern) {
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * format unix time to string
     *
     * @param unixTime    unix time
     * @param patternEnum pattern
     * @return return string date
     */
    public static String toString(long unixTime, DatePatternEnum patternEnum) {
        return toString(unixTime, patternEnum.getDatePattern());
    }

    /**
     * format date to string
     *
     * @param date    date instance
     * @param pattern date format pattern
     * @return return string date
     */
    public static String toString(Date date, String pattern) {
        Instant instant = new Date((date.getTime())).toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * format date to string
     *
     * @param date        date instance
     * @param patternEnum date format pattern
     * @return return string date
     */
    public static String toString(Date date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date    时间
     * @param pattern 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date        时间
     * @param patternEnum 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDate date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date    时间
     * @param pattern 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取时间的指定格式字符串
     *
     * @param date        时间
     * @param patternEnum 时间转字符串所需格式
     * @return string date
     */
    public static String toString(LocalDateTime date, DatePatternEnum patternEnum) {
        return toString(date, patternEnum.getDatePattern());
    }

    /**
     * 获取时间的默认格式字符串
     * "2019-10-10 13:23:23"
     *
     * @param date 时间
     * @return string date
     */
    public static String toString(Date date) {
        return toString(date, NORM_DATETIME_PATTERN);
    }

    /**
     * 获取时间的默认格式字符串
     * "2019-10-10 13:23:23"
     *
     * @param time 时间
     * @return string date
     */
    public static String toString(LocalDateTime time) {
        return toString(time, NORM_DATETIME_PATTERN);
    }

    /**
     * format string time to unix time
     *
     * @param time    string date
     * @param pattern date format pattern
     * @return return unix time
     */
    public static int toUnix(String time, String pattern) {
        LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return (int) formatted.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * format string time to unix time
     *
     * @param time        string date
     * @param patternEnum date format pattern
     * @return return unix time
     */
    public static int toUnix(String time, DatePatternEnum patternEnum) {
        return toUnix(time, patternEnum.getDatePattern());
    }

    /**
     * format string (yyyy-MM-dd HH:mm:ss) to unix time
     *
     * @param time string datetime
     * @return return unix time
     */
    public static int toUnix(String time) {
        return toUnix(time, NORM_DATETIME_PATTERN);
    }

    /**
     * 时间转时间戳
     *
     * @param date Date类型的时间
     * @return unix time
     */
    public static int toUnix(Date date) {
        return (int) date.toInstant().getEpochSecond();
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDate(String time, String pattern) {
        return toDateTime(time, pattern);
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDate(String time, DatePatternEnum datePatternEnum) {
        return toDate(time, datePatternEnum.getDatePattern());
    }

    /**
     * unix time to Date time
     *
     * @param unixTime unix time
     * @return Date 类型的时间
     */
    public static Date toDate(long unixTime) {
        return Date.from(Instant.ofEpochSecond(unixTime));
    }

    /**
     * LocalDateTime格式转为Date 格式的时间
     *
     * @param localDateTime LocalDateTime 格式的时间
     * @return Date格式的时间
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDateTime(String time, String pattern) {
        LocalDateTime formatted = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return Date.from(formatted.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串格式时间以pattern时间格式转为Date类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return Date 类型的时间
     */
    public static Date toDateTime(String time, DatePatternEnum datePatternEnum) {
        return toDateTime(time, datePatternEnum.getDatePattern());
    }

    /**
     * 字符串格式时间以pattern时间格式转为LocalDateTime类型
     *
     * @param time    string time
     * @param pattern 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串格式时间以pattern时间格式转为LocalDateTime类型
     *
     * @param time            string time
     * @param datePatternEnum 时间格式 例如 yyyy:MM:dd HH:mm:ss
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time, DatePatternEnum datePatternEnum) {
        return toLocalDateTime(time, datePatternEnum.getDatePattern());
    }

    /**
     * 字符串格式时间以默认时间格式转为LocalDateTime类型
     *
     * @param time 默认时间格式(yyyy:MM:dd HH:mm:ss)的string time
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(String time) {
        return toLocalDateTime(time, NORM_DATETIME_PATTERN);
    }

    /**
     * Date格式转为LocalDateTime 格式的时间
     *
     * @param date Date 类型的时间
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Date格式转为LocalDate 格式的时间
     *
     * @param date Date 类型的时间
     * @return LocalDate 类型的时间
     */
    public static LocalDate toLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 时间戳转 LocalDateTime 格式
     *
     * @param unixTime 时间戳格式
     * @return LocalDateTime 类型的时间
     */
    public static LocalDateTime toLocalDateTime(long unixTime) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime), ZoneId.systemDefault());
    }

    /**
     * 此时是否在区间内 默认开开
     * 方法内当前时间取到秒级(无毫秒)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return isInDateTimeRange(startTime, endTime, 0);
    }

    /**
     * 此时是否在区间内 默认开开
     * 方法内当前时间取到秒级(无毫秒)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime checkDateTime, LocalDateTime startTime, LocalDateTime endTime) {
        return isInDateTimeRange(checkDateTime, startTime, endTime, 0);
    }

    /**
     * 此时此刻是否在时间区间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  0 开开 1 开闭 2 闭开 3 闭闭
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime startTime, LocalDateTime endTime, int interval) {
        return isInDateTimeRange(LocalDateTime.now().withNano(0), startTime, endTime, interval);
    }

    /**
     * 此时此刻是否在时间区间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  0 开开 1 开闭 2 闭开 3 闭闭
     * @return true 在
     */
    public static boolean isInDateTimeRange(LocalDateTime checkDateTime, LocalDateTime startTime, LocalDateTime endTime, int interval) {
        if (interval >> 2 == 0) {
            throw new BusinessException("判断区间参数输入错误");
        }
        return ((interval >> 1) == 1 ? (!checkDateTime.isBefore(startTime)) : (checkDateTime.isAfter(startTime))) && ((interval & 1) == 1 ? (!checkDateTime.isAfter(endTime)) : (checkDateTime.isBefore(endTime)));
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date dd = format.parse("2020-01-12");

        System.out.println(getWeekNumOfYear(dd));

        System.out.println(getWeekNumOfYearLeanBack(dd));

        System.out.println(getWeekAndYear(dd));
    }

    public static String formatDateString(String sendTime) {
        String[] split = sendTime.split("-");
        if (split.length != 3) {
            return sendTime;
        }
        if (split[0].length() != 4) {
            return sendTime;
        }
        if (split[1].length() == 1) {
            split[1] = "0" + split[1];
        }
        if (split[2].length() == 1) {
            split[2] = "0" + split[2];
        }
        return split[0] + "-" + split[1] + "-" + split[2];
    }


    /**
     * 返回一个时间属于哪一年的第几周 跨年的周属于后一年
     *
     * @param date
     * @return
     */
    public static Map<String, Integer> getWeekAndYear(Date date) {
        Map<String, Integer> result = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        result.put("week", week);
        if (week == 1 && month == 12) {
            result.put("year", year + 1);
        } else {
            result.put("year", year);
        }
        return result;
    }




    /**
     * 计算当前时间是否在某个时间区间内
     *
     * @param start 开始区间
     * @param end   结束区间
     * @return 是否在区间内
     */
    public static boolean isInDateTimeRange(Date start, Date end) {
        if (DataUtil.isEmpty(start) || DataUtil.isEmpty(end)) {
            return false;
        }
        Date cur = new Date();
        return cur.after(start) && cur.before(end);
    }

    /**
     * 查询当前季度的开始月份
     *
     * @param quarterly 季度
     * @return 月份
     */
    public static Integer getQuarterlyStart(Integer quarterly) {
        if (DataUtil.isEmpty(quarterly)) {
            return null;
        } else {
            switch (quarterly) {
                case 1:
                    return 1;
                case 2:
                    return 4;
                case 3:
                    return 7;
                case 4:
                    return 10;
                default:
                    return null;
            }
        }
    }

    /**
     * 查询当前季度的结束月份
     *
     * @param quarterly 季度
     * @return 月份
     */
    public static Integer getQuarterlyEnd(Integer quarterly) {
        if (DataUtil.isEmpty(quarterly)) {
            return null;
        } else {
            switch (quarterly) {
                case 1:
                    return 3;
                case 2:
                    return 6;
                case 3:
                    return 9;
                case 4:
                    return 12;
                default:
                    return null;
            }
        }
    }

    /**
     * 计算2个日期之间相差的  相差多少月
     * 比如：2011-02-02 到  2017-03-02 相差 ，1个月
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Integer dayCompareMonth(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear - fromYear;
        int month = toMonth - fromMonth;
        return year * 12 + month;
    }
}
