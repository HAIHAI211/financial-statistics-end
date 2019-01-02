package com.persistence.financialstatisticsend.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    MASTER_NOT_EXIST(0,"月表未找到");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
