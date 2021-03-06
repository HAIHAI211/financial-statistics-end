package com.persistence.financialstatisticsend.dataobject;

import com.persistence.financialstatisticsend.enums.FinancialSexEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate
public class FinancialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private Integer userSex = FinancialSexEnum.MEN.getCode();
}
