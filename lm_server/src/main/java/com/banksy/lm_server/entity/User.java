package com.banksy.lm_server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/
@Data
public class User {
    private Integer id;
    private String userid;
    private String name;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}