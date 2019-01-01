package com.persistence.financialstatisticsend.utils;

import java.util.Random;

public class KeyUtils {

    /*
    * 生成唯一的主键
    * 格式：时间+随机数
    * */
    public static synchronized String genUniqueKey() { // 避免多线程重复，故加synchronized关键字
        Random random = new Random();
//        Integer a = random.nextInt(90) + 10; // 生成两位
        Integer number = random.nextInt(900000) + 100000; // 生成6位

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
