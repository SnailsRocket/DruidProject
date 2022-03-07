package com.xubo.druid.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author xubo
 * @Date 2022/3/7 17:08
 */
public class DateUtils {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String formatCstTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtils.getDateFormat(usDate, format);
    }

    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String calculateRemainDate(Timestamp t1, Timestamp t2){
        // 一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;
        // 一小时的毫秒数
        long nh = 1000 * 60 * 60;
        // 一分钟的毫秒数
        long nm = 1000 * 60;
        // 一秒钟的毫秒数
        long ns = 1000;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long date = t2.getTime() - t1.getTime();
        // 计算差多少天
        day = date / nd;
        // 计算差多少小时
        hour = date % nd / nh + day * 24;
        // 计算差多少分钟
        min = date % nd % nh / nm + day * 24 * 60;
        // 计算差多少秒
        sec = date % nd % nh % nm / ns;
        String result = "";
        if(day == 0){
            result = "剩余："+ (hour - day * 24) + "小时" + (min - day * 24 * 60) + "分钟";
        }else{
            result = "剩余："+ day + "天" + (hour - day * 24) + "小时" + (min - day * 24 * 60) + "分钟";
        }
        return result;
    }

    /***
     *
     * 获取当前时间与明日凌晨(00:00:00)的时间差，单位秒
     * @return
     */
    public static long timeDifference() {

        Date currentTime = new Date();
        //从一个 Instant和区域ID获得 LocalDateTime实例
        LocalDateTime localDateTime=LocalDateTime.ofInstant(currentTime.toInstant(), ZoneId.systemDefault());
        //获取第第二天零点时刻的实例
        LocalDateTime toromorrowTime=LocalDateTime.ofInstant(currentTime.toInstant(), ZoneId.systemDefault())
                .plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        //ChronoUnit日期枚举类,between方法计算两个时间对象之间的时间量
        long seconds = ChronoUnit.SECONDS.between(localDateTime, toromorrowTime);

        return seconds;
    }



    /**
     * Date转LocalDateTime
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToFullTimeSplitPattern(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return fullTimeSplitPattern(localDateTime);
    }

    public static String fullTimeSplitPattern(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_SPLIT_PATTERN);
    }

    /**
     *
     * @param min 正数是后推，负数是前推
     * @param date
     * @return
     */
    public static Date delayMinute(Integer min, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

}
