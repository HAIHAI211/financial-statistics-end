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

    private BigDecimal financialAmount;

    private List<FinancialDetail> financialDetailList;
}
