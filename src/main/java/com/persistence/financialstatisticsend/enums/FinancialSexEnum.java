package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum FinancialSexEnum {
    WOMEN(0, "女性"),
    MEN(1, "男性")
    ;
    private Integer code;
    private String message;

    FinancialSexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
