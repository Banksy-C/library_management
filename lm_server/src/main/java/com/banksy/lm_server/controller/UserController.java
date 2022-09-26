package com.banksy.lm_server.controller;

import com.banksy.lm_server.common.Result;
import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.User;
import com.banksy.lm_server.service.IUserService;
import com.banksy.lm_server.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 整表查询
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param []
     * @return com.banksy.lm_server.common.Result
     **/
    @GetMapping("/list")
    public Result getListUsers() {
        List<User> userList = userService.listUsers();
        return Result.success(userList);
    }

    /**
     * 分页查询
     * @Author banksy
     * @Date 2022/9/24 12:48 PM
     * @Param [userPageRequest]
     * @return com.banksy.lm_server.common.Result
     **/
    @GetMapping("/page")
    public Result getPage(UserPageRequest userPageRequest){
        return Result.success(userService.page(userPageRequest));
    }
}