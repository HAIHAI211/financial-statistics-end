package com.persistence.financialstatisticsend.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtilsTest {

    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.of(2018, 2, 6);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfMonth();
        DayOfWeek dow = localDate.getDayOfWeek();
        int len = localDate.lengthOfMonth();
        boolean bool = localDate.isLeapYear();
        String xx = localDate.toString();
        LocalDate today = LocalDate.now();
    }

    @Test
    public void testDateParseLocalDate() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + localDate);
    }

}