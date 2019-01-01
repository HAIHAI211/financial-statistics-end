package com.persistence.financialstatisticsend.repository;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialCategoryRepository extends JpaRepository<FinancialCategory, Integer> {
}
