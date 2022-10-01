package com.banksy.lm_server.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import com.banksy.lm_server.mapper.AdminMapper;
import com.banksy.lm_server.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author banksy
 * @Data 2022/10/1 6:52 PM
 * @Version 1.0
 **/

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public void save(Admin admin) {
        Date date = new Date();
        // 当做卡号来处理
        admin.setUsername(DateUtil.format(date, "yyyyMMdd") + IdUtil.fastSimpleUUID());
        adminMapper.save(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Date());
        adminMapper.updateById(admin);
    }

    @Override
    public List<Admin> listUsers() {
        return adminMapper.listUsers();
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public PageInfo<Admin> page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(), adminPageRequest.getPageSize());
        List<Admin> admins = adminMapper.listByCondition(adminPageRequest);
        return new PageInfo<>(admins);
    }
}