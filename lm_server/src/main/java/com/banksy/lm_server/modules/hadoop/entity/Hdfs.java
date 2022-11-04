package com.banksy.lm_server.modules.hadoop.entity;

import lombok.Data;

/**
 * @Author banksy
 * @Data 2022/10/23 6:50 PM
 * @Version 1.0
 **/
@Data
public class Hdfs {
    private String type;//文件夹、文件
    private String path;//路径
    private String name;//文件或文件夹名称
    private String permission;//权限
    private String owner;//拥有者
    private String group;//组
    private String len;//大小
    private String modificationTime;//时间
    private String replication;//分区数
    private String blockSize;//block大小
}