package com.persistence.financialstatisticsend.dto;

import lombok.Data;

@Data
public class DateInfoDTO {

    private int year;
    private int month;
    private int day;
    private String addZeroMonth;
    private String addZeroDay;
}
