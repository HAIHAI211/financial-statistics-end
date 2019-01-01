package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialDetailRepository extends JpaRepository<FinancialDetail, Integer> {
    /*根据主表id查询明细*/
    List<FinancialDetail> findByMasterIdOrderByUserIdAsc(Integer masterId);

    /*根据主表id删除明细*/
    void deleteByMasterId(Integer masterId);

    /*根据明细表id删除明细*/
    void deleteByDetailIdNotIn(List<Integer> detailIdList);
}
