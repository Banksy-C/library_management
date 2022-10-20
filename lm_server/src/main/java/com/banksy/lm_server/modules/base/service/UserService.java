package com.banksy.lm_server.modules.base.service;

import com.banksy.lm_server.modules.base.controller.request.UserPageRequest;
import com.banksy.lm_server.modules.base.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    void save(User user);

    void deleteById(String userid);

    void update(User user);

    List<User> listUsers();

    User getById(String userid);

    PageInfo<User> page(UserPageRequest userPageRequest);
}
