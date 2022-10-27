package com.banksy.lm_server.modules.hadoop.service;

import com.banksy.lm_server.modules.hadoop.controller.request.HdfsPageRequest;
import com.banksy.lm_server.modules.hadoop.entity.Hdfs;
import com.github.pagehelper.PageInfo;

public interface HdfsService {

    PageInfo<Hdfs> page(HdfsPageRequest hdfsPageRequest);
}
