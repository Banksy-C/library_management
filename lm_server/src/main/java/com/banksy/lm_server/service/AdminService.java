package com.banksy.lm_server.service;

import com.banksy.lm_server.controller.dto.LoginDTO;
import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.controller.request.LoginRequest;
import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.Admin;
import com.banksy.lm_server.entity.User;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface AdminService {


    LoginDTO login(LoginRequest request);


    void save(Admin admin);

    void deleteById(String userid);

    void update(Admin admin);

    List<Admin> listAdmins();

    Admin getById(String adminId);

    PageInfo<Admin> page(AdminPageRequest adminPageRequest);

}
