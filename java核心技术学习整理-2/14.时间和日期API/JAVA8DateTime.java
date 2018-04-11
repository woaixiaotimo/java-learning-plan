package com;

import java.time.*;
import java.time.format.DateTimeFormatter;

/*
* 转载自https://blog.csdn.net/timheath/article/details/71326329
* */
public class JAVA8DateTime {

    //日期（年月日）对应的是java.time.LocalDate
    //时间（时分秒）对应的是java.time.LocalTime
    //日期时间（年月日时分秒）对应的是java.time.LocalDateTime
    public static void localDateTime() {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now date = " + now);

        // 输出当前日期时间的年,月，日，时，分，秒，纳秒
        System.out.println("now year = " + now.getYear());


        // 将当前日期时间减去两天
        LocalDateTime dateTime2 = now.minusDays(2);
        System.out.println("now date - 2 = " + dateTime2);

        // 将当前日期时间加上五天
        LocalDateTime dateTime3 = now.plusDays(5);
        System.out.println("now date + 5 = " + dateTime3);

        // 构造一个指定日期时间的对象
        LocalDateTime dateTime = LocalDateTime.of(2016, 10, 23, 8, 20);
        System.out.println(dateTime);
    }

    //时间戳
    public static void time() {
        // 获取当前时间的时间戳
        Instant instant = Instant.now();
        // 因为中国在东八区，所以这句输出的时间跟我的电脑时间是不同的
        System.out.println(instant);

        // 既然中国在东八区，则要偏移8个小时，这样子获取到的时间才是自己电脑的时间
        OffsetDateTime dateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(dateTime);

        // 转换成毫秒，如果是当前时间的时间戳，结果跟System.currentTimeMillis()是一样的
        long milli = instant.toEpochMilli();
        System.out.println(milli);
    }

    //时间间隔-Duration
    public static void duration() {
        LocalTime start = LocalTime.now();
        try {
            //让线程睡眠3s
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        LocalTime end = LocalTime.now();
        //获取end和start的时间间隔
        Duration duration = Duration.between(start, end);

        //可能会输出PT3S或者输出PT3.001S，至于多出来的0.001秒其实就是除去线程睡眠时间执行计算时间间隔那句代码消耗的时间
        System.out.println(duration);
    }

    //日期间隔
    public static void period() {
        //起始时间指定为2015年3月4日
        LocalDate start = LocalDate.of(2015, 3, 4);
        //终止时间指定为2017年8月23日
        LocalDate end = LocalDate.of(2017, 8, 23);

        Period period = Period.between(start, end);
        //输出P2Y5M19D，Y代表年，M代表月，D代表日，说明start和end的日期间隔是2年5个月19天
        System.out.println(period);
    }

    //格式转换
    public static void dateTimeFormatter() {
// 获取预定义的格式，DateTimeFormatter类预定了很多种格式
        DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
        // 获取当前日期时间
        LocalDate now = LocalDate.now();
        // 指定格式化器格式日期时间
        String strNow = now.format(dtf);
        System.out.println(strNow);

        // 自定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String strNow2 = now.format(formatter);
        System.out.println(strNow2);

        // 将字符串转换成日期
        LocalDate date = LocalDate.parse(strNow2, formatter);
        System.out.println(date);
    }

    public static void main(String[] args) {

    }
}
