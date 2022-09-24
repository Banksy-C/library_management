package com.banksy.lm_server.service;

import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.User;

import java.util.List;

public interface IUserService {

    List<User> listUsers();

    Object page(UserPageRequest userPageRequest);

}
