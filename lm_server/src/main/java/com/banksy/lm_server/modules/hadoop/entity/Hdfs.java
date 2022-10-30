package com.banksy.lm_server.modules.hadoop.entity;

import lombok.Data;

/**
 * @Author banksy
 * @Data 2022/10/23 6:50 PM
 * @Version 1.0
 **/
@Data
public class Hdfs {
    private  String path;
    private  String name;
    private  String permission;
    private  String owner;
    private  String group;
    private  String len;
    private  String modificationTime;
    private  String replication;
    private  String blockSize;
}