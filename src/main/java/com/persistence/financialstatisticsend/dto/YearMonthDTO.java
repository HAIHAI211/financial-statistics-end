package com.persistence.financialstatisticsend.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

public class YearMonthDTO {
    private LocalDate localDate;

    public YearMonthDTO(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return localDate.toString().substring(0, 7); // 2019-01-01
    }
}
