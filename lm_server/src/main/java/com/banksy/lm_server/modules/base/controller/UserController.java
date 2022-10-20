package com.banksy.lm_server.modules.base.controller;

import com.banksy.lm_server.common.config.Result;
import com.banksy.lm_server.modules.base.controller.request.UserPageRequest;
import com.banksy.lm_server.modules.base.entity.User;
import com.banksy.lm_server.modules.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 增
     * @Author banksy
     * @Date 2022/9/27 12:45 PM
     * @Param [user]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    /**
     * 删除【根据userid】
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @DeleteMapping("/delete/{userid}")
    public Result deleteById(@PathVariable String userid) {
        userService.deleteById(userid);
        return Result.success();
    }

    /**
     * 改
     * @Author banksy
     * @Date 2022/9/27 12:48 PM
     * @Param [user]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
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
        List<User> userList = userService.listUsers();
        return Result.success(userList);
    }

    /**
     * 查【根据userId】
     * @Author banksy
     * @Date 2022/9/27 12:49 PM
     * @Param [id]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @GetMapping("/{userid}")
    public Result getById(@PathVariable String userid) {
        User user = userService.getById(userid);
        return Result.success(user);
    }

    /**
     * 查【分页】
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param [userPageRequest]
     * @return com.banksy.lm_server.common.config.Result
     **/
    @GetMapping("/page")
    public Result getPage(UserPageRequest userPageRequest){
        return Result.success(userService.page(userPageRequest));
    }
}