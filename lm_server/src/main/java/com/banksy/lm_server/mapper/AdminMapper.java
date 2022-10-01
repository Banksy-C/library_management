package com.banksy.lm_server.mapper;

import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    void save(Admin user);

    void deleteById(Integer id);

    void updateById(Admin admin);

    List<Admin> listUsers();

    Admin getById(Integer id);

    List<Admin> listByCondition(AdminPageRequest adminPageRequest);
}
