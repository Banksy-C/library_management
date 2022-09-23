package com.banksy.lm_server.entity;

import lombok.Data;

/**
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
}