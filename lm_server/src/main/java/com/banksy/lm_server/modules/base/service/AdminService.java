package com.banksy.lm_server.modules.base.service;

import com.banksy.lm_server.modules.base.controller.dto.LoginDTO;
import com.banksy.lm_server.modules.base.controller.request.AdminPageRequest;
import com.banksy.lm_server.modules.base.controller.request.LoginRequest;
import com.banksy.lm_server.modules.base.entity.Admin;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface AdminService {


    LoginDTO login(LoginRequest request);


    void save(Admin admin);

    void deleteById(String userid);

    void update(Admin admin);

    List<Admin> listAdmins();

    Admin getById(Integer adminId);

    PageInfo<Admin> page(AdminPageRequest adminPageRequest);

}
