package com.banksy.lm_server.controller;

import com.banksy.lm_server.common.Result;
import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import com.banksy.lm_server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author banksy
 * @Data 2022/10/1 6:45 PM
 * @Version 1.0
 **/
//@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    /**
     * 增
     * @Author banksy
     * @Date 2022/9/27 12:45 PM
     * @Param [admin]
     * @return com.banksy.lm_server.common.Result
     **/
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }

    /***
     * 删除【根据ID】
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.Result
     **/
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /***
     * 改
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [admin]
     * @return com.banksy.lm_server.common.Result
     **/
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    /**
     * 查【整表】
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param []
     * @return com.banksy.lm_server.common.Result
     **/
    @GetMapping("/list")
    public Result getListUsers() {
        List<Admin> adminList = adminService.listUsers();
        return Result.success(adminList);
    }

    /***
     * 查【根据ID】
     * @Author banksy
     * @Date 2022/9/27 12:49 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.Result
     **/
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    /**
     * 查【分页】
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param [userPageRequest]
     * @return com.banksy.lm_server.common.Result
     **/
    @GetMapping("/page")
    public Result getPage(AdminPageRequest adminPageRequest){
        return Result.success(adminService.page(adminPageRequest));
    }
}