package com.banksy.lm_server.common.exception;

import cn.hutool.core.util.StrUtil;
import com.banksy.lm_server.common.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandle【全局异常处理】
 * 作用：1、捕获业务层异常，可传递自定义错误代码
 *      2、捕获全局系统异常
 *
 * @Author banksy
 * @Data 2022/9/30 11:10 PM
 * @Version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e) {
        log.error("业务异常", e);
        String code = e.getCode();// 可返回自定义错误代码
        if (StrUtil.isNotBlank(code)) {
            return Result.error(code, e.getMessage());
        }
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e) {//全局最大Exception错误，未捕获的错误
        log.error("系统错误", e);
        return Result.error("系统错误");
    }

}