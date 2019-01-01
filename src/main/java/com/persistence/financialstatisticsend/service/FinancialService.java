package com.persistence.financialstatisticsend.service;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialService {
    /*创建报表*/
    FinancialDTO create(FinancialDTO financialDTO);

    /*根据月份顺序查询主报表*/
    Page<FinancialMaster> findByOrderByFinancialDateAsc(Pageable pageable);

    /*根据主表id查询明细表*/
    List<FinancialDetail> findByMasterIdOrderByUserIdAsc(Integer masterId);

    /*删除报表*/
    void delete(Integer masterId);

}
