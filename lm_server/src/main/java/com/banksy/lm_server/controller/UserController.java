package com.banksy.lm_server.controller;

import com.banksy.lm_server.entity.User;
import com.banksy.lm_server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author banksy
 * @Data 2022/9/23 9:35 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.listUsers();
    }
}