package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    MASTER_MONTH_EXIST(-1, "想要新增的月份已存在"),
    MASTER_NOT_EXIST(-2,"想要修改的月份未找到"),
    MASTER_NOT_SAME(-3, "想要修改的月份与数据库不一致")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
