package com.gree.commons.utils.date;

import java.time.format.DateTimeFormatter;

/**
 * @author iceWang 260259@gree.com.cn
 * @date Create in 14:56 2019/6/11
 * @description
 */
public class DateTimeConstants {

    public static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter YMDHMS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter YMDHM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");




}
