package com.persistence.financialstatisticsend.controller;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import com.persistence.financialstatisticsend.dataobject.FinancialDetail;
import com.persistence.financialstatisticsend.dataobject.FinancialMaster;
import com.persistence.financialstatisticsend.dataobject.FinancialUser;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import com.persistence.financialstatisticsend.enums.ResultEnum;
import com.persistence.financialstatisticsend.exception.FinancialException;
import com.persistence.financialstatisticsend.service.FinancialService;
import com.persistence.financialstatisticsend.utils.ResultVoUtils;
import com.persistence.financialstatisticsend.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financial")
@Slf4j
public class FinancialController {

    @Autowired
    FinancialService financialService;


    @PostMapping("create")
    public ResultVo create(@RequestBody FinancialDTO financialDTO){
        try {
            financialDTO = financialService.create(financialDTO);
            return ResultVoUtils.success();
        } catch (Exception e) {
            if (e instanceof FinancialException) {
                FinancialException financialException = (FinancialException) e;
                log.warn(financialException.getMessage());
                return ResultVoUtils.error(financialException.getCode(), financialException.getMessage());
            }
            throw e;
        }
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody FinancialDTO financialDTO){
        try {
            financialDTO = financialService.update(financialDTO);
            return ResultVoUtils.success();
        } catch (Exception e) {
            if (e instanceof FinancialException) {
                FinancialException financialException = (FinancialException) e;
                log.warn(financialException.getMessage());
                return ResultVoUtils.error(financialException.getCode(), financialException.getMessage());
            }
            throw e;
        }
    }


    @GetMapping("month")
    public ResultVo getMasters(Integer pageNum, Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        Page<FinancialMaster> financialMasterPage = financialService.findByOrderByFinancialDateAsc(pageRequest);
        return ResultVoUtils.success(financialMasterPage.getContent(), financialMasterPage.getTotalPages());
    }

    @GetMapping("detail")
    public ResultVo getDetails(Integer masterId) {
        Optional<FinancialMaster> financialMasterOptional = financialService.getFinancialMasterById(masterId);
        if (!financialMasterOptional.isPresent()) {
            return ResultVoUtils.error(ResultEnum.MASTER_NOT_EXIST);
        }
        List<FinancialDetail> financialDetailList = financialService.findByMasterIdOrderByUserIdAsc(masterId);

        FinancialDTO financialDTO = new FinancialDTO();
        BeanUtils.copyProperties(financialMasterOptional.get(), financialDTO);
        financialDTO.setFinancialDetailList(financialDetailList);

        return ResultVoUtils.success(financialDTO);
    }

    @GetMapping("category")
    public ResultVo getCategory(){
        List<FinancialCategory> financialCategoryList = financialService.getFinancialCategoryList();
        return ResultVoUtils.success(financialCategoryList);
    }

    @GetMapping("user")
    public ResultVo getUser(){
        List<FinancialUser> financialUserList = financialService.getFinancialUserList();
        return ResultVoUtils.success(financialUserList);
    }

}
