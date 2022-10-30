package com.banksy.lm_server.modules.hadoop.controller.request;

import com.banksy.lm_server.common.Request.BaseRequest;
import lombok.Data;

/**
 * HDFS分页请求参数，HDFS路径
 * @Author banksy
 * @Data 2022/10/26 11:09 PM
 * @Version 1.0
 **/
@Data
public class HdfsPageRequest extends BaseRequest {
    private String path;
}