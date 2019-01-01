package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialUserRepository extends JpaRepository<FinancialUser, Integer> {
}
