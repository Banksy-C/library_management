package com.banksy.lm_server.service;

import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.User;

import java.util.List;

public interface IUserService {

    void save(User user);

    void deleteById(Integer id);

    void update(User user);

    List<User> listUsers();

    User getById(Integer id);

    Object page(UserPageRequest userPageRequest);
}
