package com.banksy.lm_server.mapper;

import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    Admin getByUsername(String username);


    void save(Admin admin);

    void deleteById(String userid);

    void updateById(Admin admin);

    List<Admin> listAdmins();

    Admin getById(String userid);

    List<Admin> listByCondition(AdminPageRequest adminPageRequest);

    Integer getAdminIdDayCount(String date);
}
