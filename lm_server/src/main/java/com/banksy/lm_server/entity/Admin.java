package com.banksy.lm_server.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author banksy
 * @Data 2022/10/1 6:51 PM
 * @Version 1.0
 **/
@Data
public class Admin {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String password;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updatetime;
}