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
public class FinancialDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailId;

    private Integer masterId;

    private Integer userId;

    private Integer categoryType;

    private BigDecimal financialPrice;

    // @Temporal(TemporalType.DATE)
    private LocalDate deadline;

    /*是否已经偿还 0表示未偿还 1表示已经还完*/
    private Boolean hasPay;

    /*是否是负债 0表示负债 1表示盈利*/
    private Boolean debt;
}
