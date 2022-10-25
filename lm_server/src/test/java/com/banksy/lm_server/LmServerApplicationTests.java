package com.banksy.lm_server;

import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.modules.base.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LmServerApplicationTests {

    @Autowired
    AdminService adminService;

    @Test
    public void contextLoads() {
//        Admin admin = adminService.getById(adminId);
//        System.out.println(admin);
    }

}
