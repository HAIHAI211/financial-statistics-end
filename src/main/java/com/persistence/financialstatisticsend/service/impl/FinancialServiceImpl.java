package com.persistence.financialstatisticsend.service.impl;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dataobject.FinancialUser;
import com.persistence.financialstatisticsend.dto.DateInfoDTO;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import com.persistence.financialstatisticsend.enums.FinancialDebtEnum;
import com.persistence.financialstatisticsend.enums.FinancialPayEnum;
import com.persistence.financialstatisticsend.enums.ResultEnum;
import com.persistence.financialstatisticsend.exception.FinancialException;
import com.persistence.financialstatisticsend.repository.FinancialCategoryRepository;
import com.persistence.financialstatisticsend.repository.FinancialDetailRepository;
import com.persistence.financialstatisticsend.repository.FinancialMasterRepository;
import com.persistence.financialstatisticsend.repository.FinancialUserRepository;
import com.persistence.financialstatisticsend.service.FinancialService;
import com.persistence.financialstatisticsend.utils.CopyUtils;
import com.persistence.financialstatisticsend.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    private FinancialMasterRepository masterRepository;

    @Autowired
    private FinancialDetailRepository detailRepository;

    @Autowired
    private FinancialCategoryRepository categoryRepository;

    @Autowired
    private FinancialUserRepository userRepository;

    @Override
    public List<FinancialCategory> getFinancialCategoryList() {
        return categoryRepository.findAllByOrderByCategoryTypeAsc();
    }

    @Override
    public List<FinancialUser> getFinancialUserList() {
        return userRepository.findAllByOrderByUserIdAsc();
    }

    @Override
    @Transactional
    public FinancialDTO create(FinancialDTO financialDTO) {

        // 获取financialDate，判断数据库中是否有重复月份
        LocalDate financialDate = financialDTO.getFinancialDate();
        financialDate = financialDate.withDayOfMonth(1); // 统一设置日期为1号
        Optional<FinancialMaster> financialMasterOptional =  masterRepository.findFirstByFinancialDate(financialDate);
        if (financialMasterOptional.isPresent()) { // 新增月份重复
            throw new FinancialException(ResultEnum.MASTER_MONTH_EXIST);
        }

        // 拷贝属性DTO->dataobject(实际上只需要拷贝financialDate字段即可)
        FinancialMaster financialMaster = new FinancialMaster();
        BeanUtils.copyProperties(financialDTO, financialMaster);

        // 统计总收入
        BigDecimal financialIncome = financialDTO.getFinancialDetailList().stream()
                .filter(e -> e.getIsDebt().equals(FinancialDebtEnum.PROFIT.getCode()))
                .map(FinancialDetail::getFinancialPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 统计待还负债
        BigDecimal financialWaitDebt = financialDTO.getFinancialDetailList().stream()
                .filter(e -> e.getIsDebt().equals(FinancialDebtEnum.DEBT.getCode()) && e.getHasPay().equals(FinancialPayEnum.NO_PAY.getCode()))
                .map(FinancialDetail::getFinancialPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 统计已还负债
        BigDecimal financialClearDebt = financialDTO.getFinancialDetailList().stream()
                .filter(e -> e.getIsDebt().equals(FinancialDebtEnum.DEBT.getCode()) && e.getHasPay().equals(FinancialPayEnum.HAS_PAY.getCode()))
                .map(FinancialDetail::getFinancialPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 算出总负债
        BigDecimal financialDebt = financialWaitDebt.add(financialClearDebt);

        // 算出当月结余
        BigDecimal financialAmount = financialIncome.add(financialDebt);

        financialMaster.setFinancialIncome(financialIncome);
        financialMaster.setFinancialWaitDebt(financialWaitDebt);
        financialMaster.setFinancialClearDebt(financialClearDebt);
        financialMaster.setFinancialDebt(financialDebt);
        financialMaster.setFinancialAmount(financialAmount);

        // 存储主表
        financialMaster = masterRepository.save(financialMaster);


        // 存储明细表
        for (FinancialDetail financialDetail: financialDTO.getFinancialDetailList()){
            financialDetail.setMasterId(financialMaster.getMasterId());
            detailRepository.save(financialDetail);
        }

        return financialDTO;
    }

    @Override
    @Transactional
    public FinancialDTO update(FinancialDTO financialDTO){
        // 识别是add还是update
        Boolean isAdd = financialDTO.getMasterId() == null;
        // 存储主表
        FinancialMaster financialMaster = null;
        if (isAdd) {
            // 获取financialDate，判断数据库中是否有重复月份
            LocalDate financialDate = financialDTO.getFinancialDate();
            financialDate = financialDate.withDayOfMonth(1); // 统一设置日期为1号
            Optional<FinancialMaster> financialMasterOptional =  masterRepository.findFirstByFinancialDate(financialDate);
            if (financialMasterOptional.isPresent()) { // 新增月份重复
                throw new FinancialException(ResultEnum.MASTER_MONTH_EXIST);
            }

            // 拷贝属性DTO->dataobject
            financialMaster = new FinancialMaster();
            BeanUtils.copyProperties(financialDTO, financialMaster);

        } else {
            Optional<FinancialMaster> financialMasterOptional = masterRepository.findById(financialDTO.getMasterId());
            if (!financialMasterOptional.isPresent()){ // 未能查找到
                throw new FinancialException(ResultEnum.MASTER_NOT_EXIST);
            }
            financialMaster = financialMasterOptional.get();
            CopyUtils.copyPropertiesIgnoreNull(financialDTO, financialMaster);
        }
        // 统计收入-负债
        BigDecimal totalAmount = financialDTO.getFinancialDetailList().stream()
                .map(FinancialDetail::getFinancialPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        financialMaster.setFinancialAmount(totalAmount);
        financialMaster = masterRepository.save(financialMaster);


        if (isAdd) {
            // 如果是新增，获取financialDate，判断数据库中是否有重复月份
            // todo
        } else {
            // 如果是修改主表，则筛选出不再使用的detailIds并删除
            List<Integer> newDetailIds = financialDTO.getFinancialDetailList().stream()
                    .filter(e -> e.getDetailId() != null)
                    .map(FinancialDetail::getDetailId)
                    .collect(Collectors.toList());
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
