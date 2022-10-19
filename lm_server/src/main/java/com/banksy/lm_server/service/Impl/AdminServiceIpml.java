package com.banksy.lm_server.service.Impl;


import com.banksy.lm_server.controller.dto.LoginDTO;
import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.controller.request.LoginRequest;
import com.banksy.lm_server.entity.Admin;
import com.banksy.lm_server.exception.ServiceException;
import com.banksy.lm_server.mapper.AdminMapper;
import com.banksy.lm_server.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @Author banksy
 * @Data 2022/10/1 6:52 PM
 * @Version 1.0
 **/

@Slf4j
@Service
public class AdminServiceIpml implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public LoginDTO login(LoginRequest request) {
        Admin admin = null;
        try {
            admin = adminMapper.getByAdminId(request.getAdminId());
        } catch (Exception e) {
            log.error("根据用户名{} 查询出错", request.getAdminId());
            throw new ServiceException("用户名错误");
        }

        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);
        return loginDTO;
        //待写完

    }


    @Override
    public void save(Admin admin) {
            adminMapper.save(admin);//执行保存
    }

    @Override
    public void deleteById(String adminId) {
        adminMapper.deleteByAdminId(adminId);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdateTime(new Date());
        adminMapper.updateById(admin);
    }

    @Override
    public List<Admin> listAdmins() {
        return adminMapper.listAdmins();
    }

    @Override
    public Admin getById(String adminId) {
        return adminMapper.getById(adminId);
    }

    @Override
    public PageInfo<Admin> page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(), adminPageRequest.getPageSize());//分页
        List<Admin> admins = adminMapper.listByCondition(adminPageRequest);
        return new PageInfo<>(admins);
    }
}