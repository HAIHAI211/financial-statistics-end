package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum FinancialPayEnum {

    NO_PAY(0, "未偿还"),
    HAS_PAY(1, "已偿还")
    ;
    private Integer code;
    private String message;

    FinancialPayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
