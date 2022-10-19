package com.banksy.lm_server.controller.request;

import lombok.Data;

/**
 * login(登陆)--请求参数
 *
 * @Author banksy
 * @Data 2022/10/1 6:59 PM
 * @Version 1.0
 **/
@Data
public class LoginRequest{
    private String adminId;
    private String password;
}