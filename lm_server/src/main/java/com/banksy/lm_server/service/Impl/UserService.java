package com.banksy.lm_server.service.Impl;

import com.banksy.lm_server.entity.User;
import com.banksy.lm_server.mapper.UserMapper;
import com.banksy.lm_server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author banksy
 * @Data 2022/9/23 9:37 PM
 * @Version 1.0
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }
}