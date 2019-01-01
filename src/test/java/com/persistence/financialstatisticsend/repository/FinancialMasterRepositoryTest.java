package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dto.DateInfoDTO;
import com.persistence.financialstatisticsend.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancialMasterRepositoryTest {

    @Autowired
    private FinancialMasterRepository repository;

    @Test
    public void findOrderByFinancialDate() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<FinancialMaster> financialMasterPage = repository.findByOrderByFinancialDateAsc(pageRequest);
        Assert.assertNotEquals(0, financialMasterPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        FinancialMaster financialMaster = new FinancialMaster();
        financialMaster.setMasterId(1);
        financialMaster.setFinancialAmount(new BigDecimal(-900));
        Date date = DateUtils.getDateByStr("2017-12-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        financialMaster.setFinancialDate(date);


        FinancialMaster result = repository.save(financialMaster);
        Assert.assertNotNull(result);
    }
}