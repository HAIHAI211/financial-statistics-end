package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum FinancialCategoryEnum {
    ZHAO_LIAN(1, "招联金融"),
    JIE_BEI(2, "蚂蚁借呗"),
    HUA_BEI(3, "蚂蚁花呗"),
    ZHAO_HANG_CREDIT_CARD(4, "招行信用卡"),
    PIN_AN_CREDIT_CARD(5, "平安信用卡"),
    MIN_SHENG_CREDIT_CARD(6, "民生信用卡(海外)")
    ;
    private Integer code;
    private String message;

    FinancialCategoryEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
