package com.persistence.financialstatisticsend.dto;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class FinancialDTO {

    private Integer masterId;

    private Date financialDate;

    private BigDecimal financialAmount;

    private List<FinancialDetail> financialDetailList;
}
