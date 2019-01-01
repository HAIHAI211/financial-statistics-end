package com.persistence.financialstatisticsend.controller;

import com.persistence.financialstatisticsend.dataobject.FinancialCategory;
import com.persistence.financialstatisticsend.dto.FinancialDTO;
import com.persistence.financialstatisticsend.service.FinancialService;
import com.persistence.financialstatisticsend.utils.ResultVoUtils;
import com.persistence.financialstatisticsend.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    FinancialService financialService;


    @PostMapping("create")
    public ResultVo create(@RequestBody FinancialDTO financialDTO){
        financialDTO = financialService.create(financialDTO);
        return ResultVoUtils.success();
    }

    @GetMapping("category")
    public ResultVo getCategory(){
        List<FinancialCategory> financialCategoryList = financialService.getFinancialCategoryList();
        return ResultVoUtils.success(financialCategoryList);
    }

}
