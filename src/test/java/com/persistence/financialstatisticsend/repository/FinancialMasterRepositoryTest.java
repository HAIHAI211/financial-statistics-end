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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        // financialMaster.setMasterId(1);
        financialMaster.setFinancialAmount(new BigDecimal(-111));
        // Date date = DateUtils.getDateByStr("2017-12-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.of(2018, 1, 1);
        financialMaster.setFinancialDate(localDate);


        FinancialMaster result = repository.save(financialMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findFirstByFinancialDate() throws Exception{
        LocalDate localDate = LocalDate.of(2018,1,15);
        localDate = localDate.withDayOfMonth(1);
        Optional<FinancialMaster> financialMasterOptional = repository.findFirstByFinancialDate(localDate);
        Assert.assertNotNull(financialMasterOptional.get());
    }


    @Test
    public void findDistinctByFinancialDateBetween() throws Exception{
        LocalDate startDate = LocalDate.of(2018,1,1);
        LocalDate endDate = LocalDate.of(2018,12,31);
        List<FinancialMaster> financialMasterList = repository.findByFinancialDateBetweenOrderByFinancialDateAsc(startDate, endDate);
        Assert.assertNotEquals(0, financialMasterList.size());
    }
}