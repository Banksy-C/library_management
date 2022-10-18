package com.banksy.lm_server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String userid;
    private String name;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private String email;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date begindate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date enddate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatetime;
}