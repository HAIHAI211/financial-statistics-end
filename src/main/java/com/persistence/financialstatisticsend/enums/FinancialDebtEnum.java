package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum FinancialDebtEnum {
    PROFIT(0, "盈利"),
    DEBT(1, "负债")
    ;
    private Integer code;
    private String message;

    FinancialDebtEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
