package com.banksy.lm_server.mapper;

import com.banksy.lm_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> listUsers();
}
