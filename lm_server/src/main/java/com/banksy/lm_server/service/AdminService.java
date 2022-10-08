package com.banksy.lm_server.service;

import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface AdminService {

    void save(Admin admin);

    void deleteById(Integer id);

    void update(Admin admin);

    List<Admin> listUsers();

    Admin getById(Integer id);

    PageInfo<Admin> page(AdminPageRequest adminPageRequest);

}
