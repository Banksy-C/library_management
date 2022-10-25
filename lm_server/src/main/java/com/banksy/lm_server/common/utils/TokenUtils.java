package com.banksy.lm_server.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.banksy.lm_server.modules.base.entity.Admin;
import com.banksy.lm_server.modules.base.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具类，生成token
 *
 * @Author banksy
 * @Data 2022/10/20 6:04 PM
 * @Version 1.0
 **/
@Component
@Slf4j
public class TokenUtils {

    private static AdminService staticAdminService;

    @Resource
    private AdminService adminService;

    @PostConstruct
    public void setAdminService() {
        staticAdminService = adminService;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String payload, String signature) {
        return JWT.create().withAudience(payload) // 将 payload 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(signature)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     * /admin?token=xxxx
     * @return admin对象
     */
    public static Admin getCurrentAdmin() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //获取token
            token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                token = request.getParameter("token");
            }
            // 执行认证
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);// 若都没有的话，都获取不到，返回获取不到
                return null;
            }
            //若获取到token之后，获取到adminId；通过adminservice获取到admin信息；
            String adminId = JWT.decode(token).getAudience().get(0);//decode解开token
//            return staticAdminService.getById(Integer.valueOf(adminId));
            return staticAdminService.getById(adminId);
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}

