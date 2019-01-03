package com.persistence.financialstatisticsend.dto;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class FinancialDTO {

    private Integer masterId;

    private LocalDate financialDate;

    /*已还负债*/
    private BigDecimal financialClearDebt;

    /*待还负债*/
    private BigDecimal financialWaitDebt;

    /*总负债 = 已还负债 + 待还负债*/
    private BigDecimal financialDebt;

    /*总收入*/
    private BigDecimal financialIncome;

    /*当月结余 = 总收入-总负债*/
    private BigDecimal financialAmount;

    private List<FinancialDetail> financialDetailList;
}
