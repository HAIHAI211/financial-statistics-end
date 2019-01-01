package com.persistence.financialstatisticsend.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class FinancialMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masterId;

    private Date financialDate;

    private BigDecimal financialAmount;
}
