package com.banksy.lm_server.modules.hadoop.controller;

import com.banksy.lm_server.common.config.Result;
import com.banksy.lm_server.modules.hadoop.entity.Hdfs;
import com.banksy.lm_server.modules.hadoop.mutidatasource.HdfsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * [/detailFile]    获取文件详细信息
 * [/createFile]    创建文件夹
 * [/uploadFile]    上传文件
 * [/downloadFile]  下载文件
 * [/delFile]       删除文件
 * [/moveFile]      文件更名及移动
 *
 * 增加处理判断。全部传进来带/
 *
 * @Author banksy
 * @Data 2022/10/23 5:45 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    @Autowired
    private HdfsSource hadoopTemplate;


    /**
     * [查询路径下目录信息]
     * @author banksy
     * 2022/10/24 10:04 PM
     * @param path 默认传入'/'【若传入第一个不是/需要报错，后期增加】     接口有问题，需要排查
     * @return Result
     **/
    @GetMapping("/detailFile")
    public Result detail(@RequestParam String path){
        List<Hdfs> hdfsList = hadoopTemplate.detailFile(path);
        return Result.success(hdfsList);
    }

    /***
     * [创建文件夹]
     * @author banksy
     * 2022/10/25 9:38 PM
     * @param path
     * @return boolean
     **/
    @GetMapping("/createFile")
    public Result create(@RequestParam String path){
        boolean file = hadoopTemplate.createFile(path);
        return Result.success(file);
    }

    /**
     * [将本地文件srcFile,上传到hdfs]
     * @author banksy
     * 2022/10/12 8:51 PM
     * @param srcFile 上传本地文件地址路径(从根目录起始)
     * @return String
     **/
    @RequestMapping("/uploadFile")
    public String upload(@RequestParam String srcFile){
        hadoopTemplate.uploadFile(srcFile);
        return "copy";
    }

    /**
     * [将HDFS中文件，下载至本地]
     * @author banksy
     * 2022/10/12 8:52 PM
     * @param fileName HDFS文件名
     * @param savePath 下载本地文件地址路径
     * @return String
     **/
    @RequestMapping("/downloadFile")
    public String download(@RequestParam String fileName,@RequestParam String savePath){
        hadoopTemplate.downloadFile(fileName,savePath);
        return "download";
    }

    /**
     * []
     * @author banksy
     * 2022/10/25 9:43 PM
     * @param path
     * @return boolean
     **/
    @RequestMapping("/delFile")
    public Result del(@RequestParam String path){
        return Result.success(hadoopTemplate.delFileDir(path));
    }

}