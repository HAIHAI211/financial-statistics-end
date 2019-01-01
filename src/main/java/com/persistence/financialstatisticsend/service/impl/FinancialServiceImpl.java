package com.persistence.financialstatisticsend.service.impl;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import com.persistence.financialstatisticsend.repository.FinancialCategoryRepository;
import com.persistence.financialstatisticsend.repository.FinancialDetailRepository;
import com.persistence.financialstatisticsend.repository.FinancialMasterRepository;
import com.persistence.financialstatisticsend.service.FinancialService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    private FinancialMasterRepository masterRepository;

    @Autowired
    private FinancialDetailRepository detailRepository;

    @Autowired
    private FinancialCategoryRepository categoryRepository;

    @Override
    public List<FinancialCategory> getFinancialCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public FinancialDTO create(FinancialDTO financialDTO) {

        // 存储主表
        FinancialMaster financialMaster = new FinancialMaster();
        BeanUtils.copyProperties(financialDTO, financialMaster);
        BigDecimal totalAmount = financialDTO.getFinancialDetailList().stream()
                .map(FinancialDetail::getFinancialPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        financialMaster.setFinancialAmount(totalAmount);
        financialMaster = masterRepository.save(financialMaster);

        // 如果是修改主表，则筛选出不再使用的detailIds并删除
        if (financialDTO.getMasterId() !=null){
            List<Integer> newDetailIds = financialDTO.getFinancialDetailList().stream()
                    .filter(e -> e.getDetailId() != null)
                    .map(FinancialDetail::getDetailId)
                    .collect(Collectors.toList());
//        List<Integer> oldDetailIds = detailRepository.findByMasterIdOrderByUserIdAsc(financialMaster.getMasterId()).stream()
//                .map(FinancialDetail::getDetailId)
//                .collect(Collectors.toList());
            List<Integer> deleteDetailIds = detailRepository.findByMasterIdOrderByUserIdAsc(financialMaster.getMasterId()).stream()
                    .filter(e -> newDetailIds.contains(e.getDetailId()))
                    .map(FinancialDetail::getDetailId)
                    .collect(Collectors.toList());
            detailRepository.deleteByDetailIdNotIn(deleteDetailIds);
        }

        // 存储明细表
        for (FinancialDetail financialDetail: financialDTO.getFinancialDetailList()){
            financialDetail.setMasterId(financialMaster.getMasterId());
            detailRepository.save(financialDetail);
        }

        return financialDTO;
    }

    @Override
    public Page<FinancialMaster> findByOrderByFinancialDateAsc(Pageable pageable) {
        return masterRepository.findByOrderByFinancialDateAsc(pageable);
    }

    @Override
    public List<FinancialDetail> findByMasterIdOrderByUserIdAsc(Integer masterId) {
        return detailRepository.findByMasterIdOrderByUserIdAsc(masterId);
    }

    @Override
    public void delete(Integer masterId) {
        // todo
    }
}
