package com.banksy.lm_server.modules.base.controller.request;

import com.banksy.lm_server.common.Request.BaseRequest;
import lombok.Data;

/**
 * 用户分页--请求参数
 *
 * @Author banksy
 * @Data 2022/9/24 12:35 PM
 * @Version 1.0
 **/
@Data
public class UserPageRequest extends BaseRequest {
    private String name;
    private String phone;
}