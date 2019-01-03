package com.persistence.financialstatisticsend.dataobject;

import com.persistence.financialstatisticsend.common.LocalDateAttributeConverter;
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

    // @Temporal(TemporalType.DATE)
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
}
