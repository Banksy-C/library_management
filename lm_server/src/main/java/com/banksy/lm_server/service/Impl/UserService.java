package com.banksy.lm_server.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.User;
import com.banksy.lm_server.mapper.UserMapper;
import com.banksy.lm_server.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author banksy
 * @Data 2022/9/23 9:37 PM
 * @Version 1.0
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void save(User user) {
        Date date = new Date();
        // 当做卡号来处理
        user.setUserid(DateUtil.format(date, "yyyyMMdd") + IdUtil.fastSimpleUUID());
        userMapper.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public User getById(String userid) {
        return userMapper.getById(userid);
    }

    @Override
    public PageInfo<User> page(UserPageRequest userPageRequest) {

        PageHelper.startPage(userPageRequest.getPageNum(), userPageRequest.getPageSize());//分页
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }

}