package com.persistence.financialstatisticsend.exception;


import com.persistence.financialstatisticsend.enums.ResultEnum;
import lombok.Getter;

@Getter
public class FinancialException extends RuntimeException {
    private Integer code;

    public FinancialException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
