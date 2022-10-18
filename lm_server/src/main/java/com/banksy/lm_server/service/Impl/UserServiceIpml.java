package com.banksy.lm_server.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.banksy.lm_server.controller.request.UserPageRequest;
import com.banksy.lm_server.entity.User;
import com.banksy.lm_server.mapper.UserMapper;
import com.banksy.lm_server.service.UserService;
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
public class UserServiceIpml implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void save(User user) {
        // 当做卡号：当前日期 + 当前日期注册量+1
        String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");//当前日期
        int userIdDayCount = userMapper.getUserIdDayCount(yyyyMMdd) + 1;//获取当前日期数量 + 1

        if (userIdDayCount < 10000) { //限制日注册数量, 小于10000
            String str = String.format("%04d", userIdDayCount);
            user.setUserid(yyyyMMdd + str);
            userMapper.save(user);//执行保存
        }
    }

    @Override
    public void deleteById(String userid) {
        userMapper.deleteById(userid);
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