package com.persistence.financialstatisticsend.service.impl;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import com.persistence.financialstatisticsend.enums.FinancialUserEnum;
import com.persistence.financialstatisticsend.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialServiceImplTest {

    @Autowired
    private FinancialServiceImpl financialService;

    @Test
    public void create() throws Exception{
        FinancialDTO financialDTO = new FinancialDTO();
        financialDTO.setMasterId(14);
        financialDTO.setFinancialDate(DateUtils.getDateByStr("2018-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        List<FinancialDetail> financialDetailList = new ArrayList<>();
        for (int i=0; i<3;i++){
            FinancialDetail financialDetail = new FinancialDetail();
            if (i == 0){
                financialDetail.setDetailId(12);
            }
            financialDetail.setFinancialPrice(new BigDecimal((i+1) * -100));
            financialDetail.setCategoryType(i);
            financialDetail.setUserId(FinancialUserEnum.LANNY.getCode());
            financialDetailList.add(financialDetail);
        }
        financialDTO.setFinancialDetailList(financialDetailList);

        FinancialDTO result =  financialService.create(financialDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderByFinancialDateAsc() {
    }

    @Test
    public void findByMasterIdOrderByUserIdAsc() {
    }

    @Test
    public void delete() {
    }
}