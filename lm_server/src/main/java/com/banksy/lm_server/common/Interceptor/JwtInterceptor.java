package com.banksy.lm_server.common.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.banksy.lm_server.common.exception.ServiceException;
import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.modules.base.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器，加token处理
 *
 * @Author banksy
 * @Data 2022/10/20 9:44 PM
 * @Version 1.0
 **/
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private static final String ERROR_CODE_401 = "401";

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(ERROR_CODE_401, "无token，请重新登录");
        }

        String adminId;// 获取 token 中的adminId
        Admin admin;// 获取admin信息
        try {
            adminId = JWT.decode(token).getAudience().get(0);//decode解开token，并取到载荷
            // 根据token中的userid查询数据库
//            admin = adminService.getById(Integer.parseInt(adminId));
            admin = adminService.getById(adminId);
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(ERROR_CODE_401, errMsg);
        }

        if (admin == null) {//这里查不到，说明token是假的
            throw new ServiceException(ERROR_CODE_401, "用户不存在，请重新登录");
        }

        try {// 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(ERROR_CODE_401, "token验证失败，请重新登录");
        }
        return true;
    }
}