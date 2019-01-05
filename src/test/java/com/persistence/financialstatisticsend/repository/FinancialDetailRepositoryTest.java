package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.enums.FinancialCategoryEnum;
import com.persistence.financialstatisticsend.enums.FinancialUserEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialDetailRepositoryTest {

    @Autowired
    private FinancialDetailRepository repository;

    @Test
    public void findByMasterIdOrderByUserIdAsc() {
        List<FinancialDetail> financialDetailList = repository.findByMasterIdOrderByUserIdAsc(27);
        Assert.assertNotEquals(0, financialDetailList.size());
    }

    @Test
    public void save() {
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setUserId(FinancialUserEnum.LANNY.getCode());
        financialDetail.setCategoryType(FinancialCategoryEnum.HUA_BEI.getCode());
        financialDetail.setFinancialPrice(new BigDecimal(-8000));
        financialDetail.setMasterId(1);
        FinancialDetail result = repository.save(financialDetail);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void deleteByDetailIdNotIn(){
        repository.deleteByDetailIdNotIn(Arrays.asList(1,2,3));
        List<FinancialDetail> financialDetailList = repository.findByMasterIdOrderByUserIdAsc(1);
        Assert.assertNotEquals(0, financialDetailList.size());
    }
}