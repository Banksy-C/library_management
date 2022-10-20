package com.banksy.lm_server.modules.base.controller;

import com.banksy.lm_server.common.config.Result;
import com.banksy.lm_server.modules.base.controller.dto.LoginDTO;
import com.banksy.lm_server.modules.base.controller.request.AdminPageRequest;
import com.banksy.lm_server.modules.base.controller.request.LoginRequest;
import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.modules.base.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员管理
 * @Author banksy
 * @Data 2022/10/1 6:45 PM
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }






    /**
     * 增
     * @Author banksy
     * @Date 2022/9/27 12:45 PM
     * @Param [user]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }

    /**
     * 删除【根据ID】
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @DeleteMapping("/delete/{userid}")
    public Result deleteById(@PathVariable String adminId) {
        adminService.deleteById(adminId);
        return Result.success();
    }

    /**
     * 改
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [admin]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    /**
     * 查【整表】没用
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param []
     * @return com.banksy.lm_server.common.config.Result
     **/
    @GetMapping("/list")
    public Result getListUsers() {
        List<Admin> adminList = adminService.listAdmins();
        return Result.success(adminList);
    }

    /**
     * 查【根据adminId】
     * @Author banksy
     * @Date 2022/9/27 12:49 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @GetMapping("/{adminid}")
    public Result getById(@PathVariable Integer adminid) {
        Admin admin = adminService.getById(adminid);
        return Result.success(admin);
    }

    /**
     * 查【分页】
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param [userPageRequest]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @GetMapping("/page")
    public Result getPage(AdminPageRequest adminPageRequest){
        return Result.success(adminService.page(adminPageRequest));
    }

}