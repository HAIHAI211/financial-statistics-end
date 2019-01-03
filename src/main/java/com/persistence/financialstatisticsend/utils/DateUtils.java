package com.persistence.financialstatisticsend.utils;

import com.persistence.financialstatisticsend.dto.DateInfoDTO;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static LocalDate dateParseLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }



    public static DateInfoDTO getDateInfo(Date date){
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期

        int year = calendar.get(Calendar.YEAR);//获取年份
        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        String addZeroMonth = month < 10 ? "0" + month : month + "";
        int day = calendar.get(Calendar.DATE);//获取日
        String addZeroDay = day < 10 ? "0" + day : day + "";

        DateInfoDTO dateInfoDTO = new DateInfoDTO();
        dateInfoDTO.setYear(year);
        dateInfoDTO.setMonth(month);
        dateInfoDTO.setDay(day);
        dateInfoDTO.setAddZeroMonth(addZeroMonth);
        dateInfoDTO.setAddZeroDay(addZeroDay);

        return  dateInfoDTO;
    }
//    yyyy-MM-dd-HH-mm-ss-SSS
    public static Date getDateByStr(String dateStr, String datePattern) throws Exception{
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(datePattern);
        Date result=simpleDateFormat.parse(dateStr);
        return result;
    }



}
