package com.banksy.lm_server.exception;

/**
 * 业务异常[告诉系统，出错了，--但这里是业务(service)层的异常，而不是系统的]
 * RuntimeException：运行时异常
 *
 * @Author banksy
 * @Data 2022/9/30 11:11 PM
 * @Version 1.0
 **/
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}