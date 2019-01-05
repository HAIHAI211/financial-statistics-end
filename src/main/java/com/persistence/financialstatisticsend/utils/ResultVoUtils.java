package com.persistence.financialstatisticsend.utils;


import com.persistence.financialstatisticsend.enums.ResultEnum;
import com.persistence.financialstatisticsend.vo.ResultVo;

public class ResultVoUtils {

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo success (Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(Object data, int pageCount) {
        ResultVo resultVo = success(data);
        resultVo.setPageCount(pageCount);
        return resultVo;
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
    public static ResultVo error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }
}
