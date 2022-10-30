package com.banksy.lm_server.modules.hadoop.controller;

import com.banksy.lm_server.common.config.Result;
import com.banksy.lm_server.modules.hadoop.controller.request.HdfsPageRequest;
import com.banksy.lm_server.modules.hadoop.mutidatasource.HdfsSource;
import com.banksy.lm_server.modules.hadoop.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    HdfsService hdfsService;


    /**
     * [查询路径下目录信息]
     * @author banksy
     * 2022/10/24 10:04 PM
     * @param  hdfsPageRequest 默认传入'/'【若传入第一个不是/需要报错，后期增加】     接口有问题，需要排查
     * @return Result
     **/
    @GetMapping("/detailFile")
    public Result detail(HdfsPageRequest hdfsPageRequest){
//        List<Hdfs> hdfsList = hadoopTemplate.detailFile(path);
//        return Result.success(hdfsList);
//        System.out.println(hdfsService.page(hdfsPageRequest));
        return Result.success(hdfsService.page(hdfsPageRequest));
    }

    /***
     * [创建文件夹]
     * @author banksy
     * 2022/10/25 9:38 PM
     * @param path
     * @return boolean
     **/
    @PostMapping("/createFile")
    public Result create(@RequestBody String path){
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
     * [删除文件及文件目录]
     * @author banksy
     * 2022/10/25 9:43 PM
     * @param path
     * @return boolean
     **/
    @PostMapping("/delFile")
    public Result delete(@RequestBody String path){
        System.out.println(path);
        return Result.success(hadoopTemplate.delFileDir(path));
    }

}