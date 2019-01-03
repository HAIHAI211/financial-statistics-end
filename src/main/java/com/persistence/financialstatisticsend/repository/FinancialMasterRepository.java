package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FinancialMasterRepository extends JpaRepository<FinancialMaster, Integer> {
    /*分页（根据月份排序）*/
    Page<FinancialMaster> findByOrderByFinancialDateAsc(Pageable pageable);
    /*根据月份段查找*/
    List<FinancialMaster> findByFinancialDateBetweenOrderByFinancialDateAsc(LocalDate startDate, LocalDate endDate);
    /*判断某月份的主表是否存在*/
    Optional<FinancialMaster> findFirstByFinancialDate(LocalDate localDate);
}
