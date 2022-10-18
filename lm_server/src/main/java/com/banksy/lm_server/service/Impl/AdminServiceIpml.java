package com.banksy.lm_server.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
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
            admin = adminMapper.getByUsername(request.getUsername());
        } catch (Exception e) {
            log.error("根据用户名{} 查询出错", request.getUsername());
            throw new ServiceException("用户名错误");
        }
        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        //待写完

        return null;
    }


    @Override
    public void save(Admin admin) {
        // 当做卡号：当前日期 + 当前日期注册量+1
        String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");//当前日期
        int adminIdDayCount = adminMapper.getAdminIdDayCount(yyyyMMdd) + 1;//获取当前日期数量 + 1

        if (adminIdDayCount < 10000) { //限制日注册数量, 小于10000
            String str = String.format("%04d", adminIdDayCount);
            admin.setUserid(yyyyMMdd + str);
            adminMapper.save(admin);//执行保存
        }
    }

    @Override
    public void deleteById(String userid) {
        adminMapper.deleteById(userid);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Date());
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