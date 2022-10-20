package com.banksy.lm_server.common.exception;

/**
 * ServiceException【业务异常】继承 RuntimeException【运行时异】
 * 作用：只捕获运行时异常；获取自定义错误代码
 *
 * @Author banksy
 * @Data 2022/9/30 11:11 PM
 * @Version 1.0
 **/
public class ServiceException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

}