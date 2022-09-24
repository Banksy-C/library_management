package com.banksy.lm_server.controller.request;

import lombok.Data;

/**
 * @Author banksy
 * @Data 2022/9/24 12:35 PM
 * @Version 1.0
 **/
@Data
public class UserPageRequest extends BaseRequest{
    private String name;
    private String phone;
}