package com.banksy.lm_server.modules.hadoop.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * @Author banksy
 * @Data 2022/10/23 5:39 PM
 * @Version 1.0
 **/
@Configuration
@ConditionalOnProperty(name="hdfs.name-node")
@Slf4j
public class HdfsClientConfig {

    @Value("${hdfs.name-node}")
    private String nameNode;

    @Value("${hdfs.username}")
    private String username;

    /**
     * Configuration conf=new Configuration（）；
     * 创建一个Configuration对象时，其构造方法会默认加载hadoop中的两个配置文件，
     * 分别是hdfs-site.xml以及core-site.xml，这两个文件中会有访问hdfs所需的参数值，
     * 主要是fs.default.name，指定了hdfs的地址，有了这个地址客户端就可以通过这个地址访问hdfs。
     * 即可理解为configuration就是hadoop中的配置信息。
     * @return
     */
    @Bean("fileSystem")
    public FileSystem createFs(){
        // TODO 创建读取配置文件
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        // 集群nn地址
        conf.set("fs.defaultFS", nameNode);
        // 设置副本数量为1
        conf.set("dfs.replication", "1");
        // 获取文件系统
        FileSystem fs = null;
        try {
            URI uri = new URI(nameNode.trim());// 连接的集群nn地址
            fs = FileSystem.get(uri,conf,username);// 获取到客户端对象
        } catch (Exception e) {
            log.error("HDFS文件系统获取失败！！！", e);
        }
        System.out.println("fs.defaultFS: "+conf.get("fs.defaultFS"));//打印默认文件系统地址
        //返回客户端对象
        return  fs;
    }
}