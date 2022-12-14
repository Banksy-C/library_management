package com.banksy.lm_server.common.config;

import lombok.Data;

/**
 * 统一返回结果封装
 *
 * @Author banksy
 * @Data 2022/9/23 10:05 PM
 * @Version 1.0
 **/
@Data
public class Result {
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "-1";

    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}