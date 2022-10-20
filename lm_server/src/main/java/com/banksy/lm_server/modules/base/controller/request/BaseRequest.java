package com.banksy.lm_server.controller.request;

import lombok.Data;

/**
 * 基础【基本】--请求参数
 *
 * @Author banksy
 * @Data 2022/9/24 12:35 PM
 * @Version 1.0
 **/
@Data
public class BaseRequest {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}