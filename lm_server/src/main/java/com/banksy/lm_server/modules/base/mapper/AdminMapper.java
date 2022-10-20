package com.banksy.lm_server.mapper;

import com.banksy.lm_server.controller.request.AdminPageRequest;
import com.banksy.lm_server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    Admin getByAdminId(String adminId);


    void save(Admin admin);

    void deleteByAdminId(String adminId);

    void updateById(Admin admin);

    List<Admin> listAdmins();

    Admin getById(String userid);

    List<Admin> listByCondition(AdminPageRequest adminPageRequest);

    Integer getAdminIdDayCount(String date);
}
