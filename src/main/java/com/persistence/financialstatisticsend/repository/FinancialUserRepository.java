package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialUserRepository extends JpaRepository<FinancialUser, Integer> {
    List<FinancialUser> findAllByOrderByUserIdAsc();
}
