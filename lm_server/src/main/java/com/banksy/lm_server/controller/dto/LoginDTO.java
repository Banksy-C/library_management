package com.banksy.lm_server.controller.dto;

import lombok.Data;

/**
 * 登陆-返回参数
 * @Author banksy
 * @Data 2022/9/30 9:33 PM
 * @Version 1.0
 **/
@Data
public class LoginDTO {
    private Integer id;
    private String username;
    private String phone;
    private String email;
}