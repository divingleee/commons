package com.gree.commons.utils.date;


import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.util.Date;

/**
 * @author divinglee
 */

public class DateTimeUtilsTest {
    @Test
    public void testLocalDateTimeToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        Assert.assertTrue(DateTimeUtils.localDateTimeToString(localDateTime).equals("2019-06-11 17:35:15"));
        Assert.assertTrue(DateTimeUtils.localDateTimeToString(localDateTime, DateTimeConstants.YMDHM_FORMATTER).equals("2019-06-11 17:35"));
        Assert.assertTrue(DateTimeUtils.localDateTimeToString(localDateTime, DateTimeConstants.YMD_FORMATTER).equals("2019-06-11"));
    }

    @Test
    public void testLocalDateTimeToTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        Long timestamp = DateTimeUtils.localDateTimeToTimestamp(localDateTime);
        Assert.assertTrue(timestamp == 1560245715000l);
    }

    @Test
    public void testLocalDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        Date date = DateTimeUtils.localDateTimeToDate(localDateTime);
        Assert.assertTrue(date.getYear() == 119);
        Assert.assertTrue(date.getMonth() == 5);
        Assert.assertTrue(date.getDate() == 11);
        Assert.assertTrue(date.getHours() == 17);
        Assert.assertTrue(date.getMinutes() == 35);
        Assert.assertTrue(date.getSeconds() == 15);
    }

    @Test
    public void testTimestampToDate() {
        Long timestamp = 1561701983163l;
        Date date = DateTimeUtils.timestampToDate(timestamp);
        Assert.assertTrue(date.getYear() == 119);
        Assert.assertTrue(date.getMonth() == 5);
        Assert.assertTrue(date.getDate() == 28);
        Assert.assertTrue(date.getHours() == 14);
        Assert.assertTrue(date.getMinutes() == 6);
        Assert.assertTrue(date.getSeconds() == 23);
    }

    @Test
    public void testDateToString() {
        Date date = new Date(119, 5, 11, 17, 35, 15);
        String result = DateTimeUtils.dateToString(date, ZoneId.systemDefault(), DateTimeConstants.YMD_FORMATTER);
        String result1 = DateTimeUtils.dateToString(date);
        Assert.assertTrue(result.equals("2019-06-11"));
        Assert.assertTrue(result1.equals("2019-06-11 17:35:15"));
    }

    @Test
    public void testDateToLocalDateTime() {
        Date date = new Date(119, 5, 11, 17, 35, 15);
        LocalDateTime localDateTime = DateTimeUtils.dateToLocalDateTime(date);
        Assert.assertTrue(localDateTime.getYear() == 2019);
        Assert.assertTrue(localDateTime.getMonth().getValue() == 6);
        Assert.assertTrue(localDateTime.getDayOfMonth() == 11);
        Assert.assertTrue(localDateTime.getHour() == 17);
        Assert.assertTrue(localDateTime.getMinute() == 35);
        Assert.assertTrue(localDateTime.getSecond() == 15);
        LocalDateTime localDateTime2 = DateTimeUtils.dateToLocalDateTime(date, ZoneId.systemDefault());
        Assert.assertTrue(localDateTime2.getYear() == 2019);
        Assert.assertTrue(localDateTime2.getMonth().getValue() == 6);
        Assert.assertTrue(localDateTime2.getDayOfMonth() == 11);
        Assert.assertTrue(localDateTime2.getHour() == 17);
        Assert.assertTrue(localDateTime2.getMinute() == 35);
        Assert.assertTrue(localDateTime2.getSecond() == 15);
    }

    @Test
    public void testStringToLocalDateTime() {
        String now = "2019-06-11 17:35:15";
        LocalDateTime localDateTime = DateTimeUtils.stringToLocalDateTime(now);
        Assert.assertTrue(localDateTime.getYear() == 2019);
        Assert.assertTrue(localDateTime.getMonth().getValue() == 6);
        Assert.assertTrue(localDateTime.getDayOfMonth() == 11);
        Assert.assertTrue(localDateTime.getHour() == 17);
        Assert.assertTrue(localDateTime.getMinute() == 35);
        Assert.assertTrue(localDateTime.getSecond() == 15);
        String now2 = "2019-06-11 17:35";
        LocalDateTime localDateTime2 = DateTimeUtils.stringToLocalDateTime(now2, DateTimeConstants.YMDHM_FORMATTER);
        Assert.assertTrue(localDateTime2.getYear() == 2019);
        Assert.assertTrue(localDateTime2.getMonth().getValue() == 6);
        Assert.assertTrue(localDateTime2.getDayOfMonth() == 11);
        Assert.assertTrue(localDateTime2.getHour() == 17);
        Assert.assertTrue(localDateTime2.getMinute() == 35);
    }

    @Test
    public void testStringToLocalDate() {
        String now = "2019-06-11";
        LocalDate localDate = DateTimeUtils.stringToLocalDate(now);
        Assert.assertTrue(localDate.getYear() == 2019);
        Assert.assertTrue(localDate.getMonth().getValue() == 6);
        Assert.assertTrue(localDate.getDayOfMonth() == 11);
    }

    @Test
    public void testLocalDateToString() {
        LocalDate localDate = LocalDate.of(2019, 6, 11);
        String string = DateTimeUtils.localDateToString(localDate);
        Assert.assertTrue(string.equals("2019-06-11"));
    }

    @Test
    public void testTimestampToLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        Instant instant = localDateTime.atZone(ZoneOffset.systemDefault()).toInstant();
        long timestamp = instant.toEpochMilli();
        LocalDateTime result = DateTimeUtils.timestampToLocalDateTime(timestamp);
        Assert.assertTrue(result.getYear() == 2019);
        Assert.assertTrue(result.getMonth().getValue() == 6);
        Assert.assertTrue(result.getDayOfMonth() == 11);
        Assert.assertTrue(result.getHour() == 17);
        Assert.assertTrue(result.getMinute() == 35);
        Assert.assertTrue(result.getSecond() == 15);
    }

    @Test
    public void testLocalDateToTimestamp() {
        LocalDate localDate = LocalDate.of(2019, 6, 11);
        Long timestamp = DateTimeUtils.localDateToTimestamp(localDate);
        long expect = 1560182400000l;
        Assert.assertTrue(timestamp == expect);
    }

    @Test
    public void testTimestampToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        long timestamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String YMDHMS = DateTimeUtils.timestampToString(timestamp);
        String YMDHM = DateTimeUtils.timestampToString(timestamp, ZoneId.systemDefault(), DateTimeConstants.YMDHM_FORMATTER);
        Assert.assertTrue(YMDHMS.equals("2019-06-11 17:35:15"));
        Assert.assertTrue(YMDHM.equals("2019-06-11 17:35"));
    }

    @Test
    public void testGetFirstDayOfMonth() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        LocalDate result = DateTimeUtils.getFirstDayOfMonth(localDateTime);
        Assert.assertTrue(result.getYear() == 2019);
        Assert.assertTrue(result.getMonth().getValue() == 6);
        Assert.assertTrue(result.getDayOfMonth() == 1);
    }

    @Test
    public void testGetQuarter() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
        DateEnum result = DateTimeUtils.getQuarter(localDateTime);
        Assert.assertTrue(result.code == DateEnum.SECOND.code);
    }

}
