package com.banksy.lm_server;

import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.modules.base.service.AdminService;
import com.banksy.lm_server.modules.hadoop.mutidatasource.HdfsSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LmServerApplicationTests {

    @Autowired
    AdminService adminService;

    @Autowired
    HdfsSource hdfsSource;

    @Test
    public void contextLoads() {
//        Admin admin = adminService.getById(adminId);
//        System.out.println(admin);
    }

    @Test
    public void path () {
        String path = "root";
        System.out.println(hdfsSource.normPath(path, false));
        // /mydata/root

        String path2 = "/root";
        System.out.println(hdfsSource.normPath(path2, false));

        // hdfs://172.16.123.11:8020/mydata/root
        String path3 = "root";
        System.out.println(hdfsSource.normPath(path3, true));

        String path4 = "/root";
        System.out.println(hdfsSource.normPath(path4, true));

        String path5 = "";
        System.out.println(hdfsSource.normPath(path5, false));
    }

    @Test
    public void test () {
        String path = "/";
        System.out.println(hdfsSource.getFilesInformation(path));
    }

}
