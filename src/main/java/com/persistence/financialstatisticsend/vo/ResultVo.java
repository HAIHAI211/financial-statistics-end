package com.persistence.financialstatisticsend.vo;

import lombok.Data;

/*http请求返回的最外层对象*/
@Data
public class ResultVo<T> {
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*总页码（分页时用到）*/
    private Integer pageCount;
    /*具体内容*/
    private T data;
}
