package com.persistence.financialstatisticsend.service;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dataobject.FinancialUser;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FinancialService {
    /*创建报表*/
    FinancialDTO create(FinancialDTO financialDTO);

    /*根据月份顺序查询主表*/
    Page<FinancialMaster> getFinancialMasterByMonth(Pageable pageable);

    /*根据主表id查询主表*/
    Optional<FinancialMaster> getFinancialMasterById(Integer masterId);

    /*根据主表id查询明细表(多个筛选条件)*/
    List<FinancialDetail> getFinancialDetailsOrder(Integer masterId);

    /*获取类别*/
    List<FinancialCategory> getFinancialCategoryList();

    /*获取用户*/
    List<FinancialUser> getFinancialUserList();

    /*更新报表*/
    FinancialDTO update(FinancialDTO financialDTO);

}
