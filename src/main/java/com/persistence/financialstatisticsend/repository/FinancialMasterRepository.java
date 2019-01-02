package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FinancialMasterRepository extends JpaRepository<FinancialMaster, Integer> {
    Page<FinancialMaster> findByOrderByFinancialDateAsc(Pageable pageable);
    List<FinancialMaster> findByFinancialDateBetween(Date startDate, Date endDate);
    Optional<FinancialMaster> findFirstByFinancialDateBetween(Date startDate, Date endDate);
}
