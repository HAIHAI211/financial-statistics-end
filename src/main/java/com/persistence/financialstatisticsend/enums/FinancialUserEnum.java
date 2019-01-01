package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum FinancialUserEnum {
    HARRISON(1, "harrison"),
    LANNY(2, "lanny")
    ;
    private Integer code;
    private String message;

    FinancialUserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
