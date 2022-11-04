package com.banksy.lm_server.modules.hadoop.mutidatasource;

import com.banksy.lm_server.modules.hadoop.entity.Hdfs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * [detailFile]    获取目录下，文件详细信息--1
 * [createFile]    创建文件夹--1
 * [/uploadFile]    上传文件
 * [/downloadFile]  下载文件
 * [/delFile]       删除文件
 * [/moveFile]      文件更名及移动
 *
 * @Author banksy
 * @Data 2022/10/23 5:44 PM
 * @Version 1.0
 **/
@Component
@ConditionalOnBean(FileSystem.class)
@Slf4j
public class HdfsSource {

    @Autowired
    private FileSystem fileSystem;

    @Value("${hdfs.name-node}")
    private String nameNode;

    /* 系统主目录 */
    @Value("${hdfs.namespace:/}")
    private String nameSpace;

    public String getNameSpace(){
        return nameSpace;
    }

    @PostConstruct//被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行。
    public void init(){//系统初始化之前看一下路径是否存在，不存在就创建出来
        existDir("/",true);
    }


    /**
     * [标准化路径；但不判空，若空：返回主目录]
     * @author banksy
     * @param originalPath 传入待标准化的路径参数
     * @param prefix true为获取-服务器路径，false为获取-主目录路径
     * @return String is not null
     **/
    public String normPath (String originalPath, boolean prefix) {
        String resultPath = null;
        if (prefix) {//获取-服务器路径
            if (originalPath.startsWith("/")) {
                resultPath = nameNode + nameSpace + originalPath;
            }else {
                resultPath = nameNode + nameSpace + '/' + originalPath;
            }
        }else {//获取-主目录路径
            if (originalPath.startsWith("/")) {
                resultPath = nameSpace + originalPath;
            }else {
                resultPath = nameSpace + '/' + originalPath;
            }
        }
        return resultPath;
    }

    /** 创建文件夹 **/
    public boolean createFile(String srcFile){
        return existDir(srcFile,true);
    }

    /**
     * 创建文件夹 & 判断路径是否存在
     * @param Path 文件路径，传入 /mydata/ 后的地址
     * @param create  true为创建文件，false判断路径是否存在
     * @return
     */
    private boolean existDir(String Path, boolean create){
        boolean flag = false;
        if(StringUtils.isBlank(Path)){//返回字符串，判断路径是否为空
            throw new IllegalArgumentException("filePath不能为空");
        }
        try{
            Path path = new Path(normPath(Path, false));
            System.out.println(path);

            if (create){
                if (!fileSystem.exists(path)){
                    fileSystem.mkdirs(path);
                }//若存在路径打印已存在
            }
            if (fileSystem.exists(path)){//判断路径是否存在
                flag = true;
            }
        }catch (Exception e){
            log.error("创建失败！", e);
        }
        return flag;
    }


    /** 获取路径下，所有文件详细信息 **/
    public List<Hdfs> detailFile(String srcFile){
        return getFilesInformation(srcFile);
    }

    /**
     * [获取路径下目录信息]
     * @author banksy
     * 2022/10/24 10:29 PM
     * @param srcFile
     * @return List<Hdfs>
     **/
    public List<Hdfs> getFilesInformation(String srcFile) {
        List<Hdfs> hdfsList = new ArrayList<>();
        String destPath = null;//目的路径
        // 目的路径
        if(StringUtils.isBlank(srcFile)) {//若srcFile为空，转到制定根路径
            destPath = nameSpace;
        }
        if (StringUtils.isNotBlank(srcFile)) {//若srcFile不为空
            destPath = normPath(srcFile, false);
        }
        //循环从数组中取出来放进list里
        try {
            FileStatus[] statuses = fileSystem.listStatus(new Path(destPath));
            for (FileStatus status : statuses) {
                Hdfs hdfs = new Hdfs();

                if (status.isFile()) { hdfs.setType("file");}
                if (status.isDirectory()) { hdfs.setType("directory");}
                //当前路径，不包含服务器地址的路径
                hdfs.setPath(StringUtils.substringAfter(status.getPath().toString(), nameNode + nameSpace));
                hdfs.setName(status.getPath().getName());
                hdfs.setPermission(String.valueOf(status.getPermission()));
                hdfs.setOwner(status.getOwner());
                hdfs.setGroup(status.getGroup());
                hdfs.setLen(String.valueOf(status.getLen()));
                hdfs.setModificationTime(String.valueOf(status.getModificationTime()));
                hdfs.setReplication(String.valueOf(status.getReplication()));
                hdfs.setBlockSize(String.valueOf(status.getBlockSize()));
//                System.out.println(hdfs);
                hdfsList.add(hdfs);
            }
//            hdfsList.addAll(Arrays.stream(statuses).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hdfsList;
    }


    /** 文件上传 **/
    public void uploadFile(String srcFile){
        //不删除源文件，允许覆盖，传入上传文件路径
        copyFileToHDFS(false,true,srcFile,nameSpace);
    }

    public void uploadFile(boolean del,String srcFile){
        //可选择是否删除源文件，允许覆盖，传入上传文件路径
        copyFileToHDFS(del,true,srcFile,nameSpace);
    }

    public void uploadFile(String srcFile,String destPath){
        //不删除源文件，允许覆盖，传入上传文件路径，传入目的文件路径
        copyFileToHDFS(false,true,srcFile,destPath);
    }

    public void uploadFile(boolean del,String srcFile,String destPath){
        //可选择是否删除源文件，允许覆盖，传入上传文件路径，传入目的文件路径
        copyFileToHDFS(del,true,srcFile,destPath);
    }

    /**
     * 文件上传至 HDFS
     * @param delSrc       指是否删除源文件，true为删除，默认为false
     * @param overwrite    是否允许覆盖
     * @param srcFile      源文件，上传文件路径
     * @param destPath     hdfs的目的路径
     */
    public  void copyFileToHDFS(boolean delSrc, boolean overwrite,String srcFile,String destPath) {
        Path srcPath = new Path(srcFile);
        // 目的路径
        if(StringUtils.isNotBlank(nameNode)){
            destPath = nameNode + destPath;
        }
        Path dstPath = new Path(destPath);
        // 实现文件上传
        try {
            // 获取FileSystem对象
            //参数解读：参数一：表示删除原数据；参数二：是否允许覆盖；参数三：原数据路径；参数四：目的地路径；
            fileSystem.copyFromLocalFile(srcPath, dstPath);
            fileSystem.copyFromLocalFile(delSrc,overwrite,srcPath, dstPath);
            //释放资源
//            fileSystem.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }



    /** 文件下载 **/
    public void downloadFile(String fileName,String savePath){
        copyFileToLocal(nameSpace+"/"+fileName,savePath);
    }


    /**
     * 从 HDFS 下载文件
     *
     * @param hdfsFile hdfs上文件路径
     * @param destPath 文件下载后,存放地址
     */
    public void copyFileToLocal(String hdfsFile,String destPath) {
        // 源文件路径
        if(StringUtils.isNotBlank(nameNode)){
            hdfsFile = nameNode + hdfsFile;
        }
        Path hdfsPath = new Path(hdfsFile);
        Path dstPath = new Path(destPath);
        try {
            // 下载hdfs上的文件
            fileSystem.copyToLocalFile(hdfsPath, dstPath);
            // 释放资源
            // fs.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }


    /** 文件及文件夹删除 **/
    public boolean delFileDir(String path){
        return rmdir(path);
    }

    /**
     * 删除文件或者文件目录
     * @param path 要删除的路径
     **/
    private boolean rmdir(String path) {
        boolean flag = false;
        if(StringUtils.isNotBlank(path)){//若路径不为空，进入
            Path destPath = new Path(normPath(path, false));
            try {
                //参数解读：参数一：要删除的路径；参数二：是否递归删除；
                fileSystem.delete(destPath,true);
                if (!fileSystem.exists(destPath)){//路径不存在说明删除成功
                    flag = true;
                }
            } catch (IllegalArgumentException | IOException e) {
                log.error("删除失败！", e);
            }
        }
        return flag;
    }

}