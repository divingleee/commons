package com.mg.commons.utils.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author divinglee
 * @date Create in 14:42 2019/6/11
 * @description
 * <p>
 * Java8 之后，推荐使用新出的日期类型
 * LocalDateTime 代替 Date
 * Instant 表示时间戳
 * Duration 表示时间段，时间戳来衡量时间段
 * Period 类似于 Duration，年月日衡量时间段
 * ZoneId 时区
 * </p>
 */
public class DateTimeUtils {

    /**
     * LocalDateTime --------------------------------》 String
     * 根据插入的LocalDateTime -》 String（yyyy-MM-dd HH:mm:ss）
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTimeToString(localDateTime, DateTimeConstants.YMDHMS_FORMATTER);
    }

    /**
     * 根据传入的 DateTimeFormatter 格式转换 LocalDateTime 成 String 返回
     *
     * @param localDateTime
     * @param dateTimeFormatter
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * LocalDateTime --------------------------------》 timestamp
     * 根据默认时区转换时间戳返回
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTimeToTimestamp(localDateTime, ZoneId.systemDefault());
    }

    /**
     * 根据插入的时区转换成时间戳返回
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime --------------------------------》 Date
     * 根据默认时区将LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return DateTimeUtils.localDateTimeToDate(localDateTime, ZoneId.systemDefault());
    }


    /**
     * LocalDateTime --------------------------------》 Date
     * 根据插入的时区将LocalDateTime转换成Date
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }


    //====================================================================================================================================

    /**
     * timestamp  ——————》 时间格式（LocalDateTime）
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        return DateTimeUtils.timestampToLocalDateTime(timestamp, ZoneId.systemDefault());
    }

    /**
     * timestamp  ——————》 时间格式（LocalDateTime）
     *
     * @param timestamp
     * @param zoneId
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }

    /**
     * timestamp  ————————————》  String
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(Long timestamp) {
        return timestampToString(timestamp, ZoneId.systemDefault(), DateTimeConstants.YMDHMS_FORMATTER);
    }

    public static String timestampToString(Long timestamp, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return localDateTimeToString(LocalDateTime.ofInstant(instant, zoneId), dateTimeFormatter);
    }

    /**
     * timestamp ——————————————————————————》 Date
     * Date 是没有时区概念的  因此直接用时间戳进行转换即可
     *
     * @param timestamp
     * @return
     */
    public static Date timestampToDate(Long timestamp) {
        return Date.from(Instant.ofEpochMilli(timestamp));
    }


    //======================================================================================================

    /**
     * date  -----------------------> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return dateToLocalDateTime(date, ZoneId.systemDefault());
    }

    public static LocalDateTime dateToLocalDateTime(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    /**
     * date  --------------->   String
     * date 是一种时间戳类型，其转成 String 类型需要指定特定的 时区 和转换 字符串类型
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ZoneId.systemDefault(), DateTimeConstants.YMDHMS_FORMATTER);
    }

    public static String dateToString(Date date, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
        return dateToLocalDateTime(date, zoneId).format(dateTimeFormatter);
    }


    // ===================================================================

    /**
     * string ------------------> LocalDateTime
     *
     * @param string
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String string) {
        return LocalDateTime.parse(string, DateTimeConstants.YMDHMS_FORMATTER);
    }


    public static LocalDateTime stringToLocalDateTime(String string, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(string, dateTimeFormatter);
    }

    public static LocalDate stringToLocalDate(String string) {
        return LocalDate.parse(string);
    }

    public static String localDateToString(LocalDate localDate) {
        return localDate.format(DateTimeConstants.YMD_FORMATTER);
    }

    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate.atTime(LocalTime.of(0, 0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    // ==========================================================

    /**
     * 获取每月的第一天
     *
     * @param localDateTime
     * @return
     */
    public static LocalDate getFirstDayOfMonth(LocalDateTime localDateTime) {
        return LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), 1);
    }


    /**
     * 获取当前日期是哪一个季度
     *
     * @return
     */
    public static DateEnum getQuarter(LocalDateTime localDateTime) {
        return DateEnum.of((localDateTime.getMonthValue() - 1) / 3 + 1);
    }

}

