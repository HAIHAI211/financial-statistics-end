package com.persistence.financialstatisticsend.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class FinancialMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer masterId;

    private LocalDate financialDate;

    private BigDecimal financialAmount;
}
