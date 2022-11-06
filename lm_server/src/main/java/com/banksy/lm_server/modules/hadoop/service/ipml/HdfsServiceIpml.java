package com.banksy.lm_server.modules.hadoop.service.ipml;

import com.banksy.lm_server.modules.hadoop.controller.request.HdfsPageRequest;
import com.banksy.lm_server.modules.hadoop.entity.Hdfs;
import com.banksy.lm_server.modules.hadoop.mutidatasource.HdfsSource;
import com.banksy.lm_server.modules.hadoop.service.HdfsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author banksy
 * @Data 2022/10/26 11:04 PM
 * @Version 1.0
 **/
@Service
public class HdfsServiceIpml implements HdfsService {

    @Autowired
    HdfsSource hdfsSource;

    @Override
    public PageInfo<Hdfs> page(HdfsPageRequest hdfsPageRequest) {
        PageHelper.startPage(hdfsPageRequest.getPageNum(), hdfsPageRequest.getPageSize());//分页
        List<Hdfs> hdfsList = hdfsSource.detailPathDir(hdfsPageRequest.getPath());
        return new PageInfo<>(hdfsList);
    }
}