package com.banksy.lm_server.modules.base.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.banksy.lm_server.common.utils.TokenUtils;
import com.banksy.lm_server.modules.base.controller.dto.LoginDTO;
import com.banksy.lm_server.modules.base.controller.request.AdminPageRequest;
import com.banksy.lm_server.modules.base.controller.request.LoginRequest;
import com.banksy.lm_server.modules.base.mapper.AdminMapper;
import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.common.exception.ServiceException;
import com.banksy.lm_server.modules.base.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "everything";

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
            throw new ServiceException("用户名不存在");
        }

        // 判断密码是否合法
        String securePass = securePass(request.getPassword());


        if (!securePass.equals(admin.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
//        if (!admin.isStatus()) {
//            throw new ServiceException("当前用户处于禁用状态，请联系管理员");
//        }

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);

        // 生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getAdminId()), admin.getPassword());
        System.out.println(token);//token查看
        loginDTO.setToken(token);
        return loginDTO;
    }


    @Override
    public void save(Admin admin) {
        // 默认密码 123
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword(DEFAULT_PASS);
        }
        admin.setPassword(securePass(admin.getPassword()));  // 设置md5加密(加盐)
        try {
            adminMapper.save(admin);//执行保存
        } catch (DuplicateKeyException e) {
            log.error("数据插入失败， getAdminId:{}", admin.getAdminId(), e);
            throw new ServiceException("用户名重复");
        }
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


    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);// 设置md5加密，加盐
    }

}