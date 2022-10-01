package com.banksy.lm_server.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;

/**
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String userid;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private DateTime begindate;
    private Date enddate;
    private Date updatetime;
}