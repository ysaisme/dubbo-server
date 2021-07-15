package com.ysa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

/**
 * @Author: ysd
 * @Description: 日期格式化对象
 * @Date: Created in 13:12 2019/11/11
 * Modified By:
 */
@AllArgsConstructor
@Getter
@ToString
public enum DatePatternEnum {

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    NORM_DATE_PATTERN("yyyy-MM-dd"),

    /**
     * 非常用日期格式：yyyy/MM/dd
     */
    ABNORMAL_DATE_PATTERN("yyyy/MM/dd"),

    /**
     * 标准时间格式：HH:mm:ss
     */
    NORM_TIME_PATTERN("HH:mm:ss"),

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    NORM_DATETIME_MINUTE_PATTERN("yyyy-MM-dd HH:mm"),

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    NORM_DATETIME_PATTERN("yyyy-MM-dd HH:mm:ss"),

    /**
     * 标准日期时间格式，精确到天：yyyy-MM-dd 00:00:00
     */
    NORM_DATETIME_PATTERN_DAY("yyyy-MM-dd 00:00:00"),

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    NORM_DATETIME_MS_PATTERN("yyyy-MM-dd HH:mm:ss.SSS"),


    /**
     * 标准中文日期格式，yyyy年MM月dd日
     */
    NORM_DATE_CH_PATTERN("yyyy年MM月dd日"),

    /**
     * 标准中文日期格式, yyyy年MM月dd日 HH:mm:ss
     */
    NORM_DATETIME_CH_PATTERN("yyyy年MM月dd日 HH:mm:ss"),

    /**
     * 标准日期格式：yyyyMMdd
     */
    PURE_DATE_PATTERN("yyyyMMdd"),

    /**
     * 标准日期格式：HHmmss
     */
    PURE_TIME_PATTERN("HHmmss"),

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    PURE_DATETIME_PATTERN("yyyyMMddHHmmss"),

    /**
     * 标准日期格式：yyyyMMddHHmmssSSS
     */
    PURE_DATETIME_MS_PATTERN("yyyyMMddHHmmssSSS"),
    ;


    /**
     * 日期格式化
     */
    private String datePattern;

    /**
     * 获取日期格式
     *
     * @param datePattern pattern
     * @return 日期格式
     */
    public DateTimeFormatter getDateTimeFormatter(DatePatternEnum datePattern) {
        return DateTimeFormatter.ofPattern(datePattern.getDatePattern());
    }
}

