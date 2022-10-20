package com.banksy.lm_server.modules.base.mapper;

import com.banksy.lm_server.modules.base.controller.request.UserPageRequest;
import com.banksy.lm_server.modules.base.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    void deleteById(String userid);

    void updateById(User user);

    List<User> listUsers();

    User getById(String userid);

    List<User> listByCondition(UserPageRequest userPageRequest);

    Integer getUserIdDayCount(String date);
}
