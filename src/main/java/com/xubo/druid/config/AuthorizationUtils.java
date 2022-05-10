package com.xubo.druid.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * @Author xubo
 * @Date 2022/5/10 10:05
 */
public class AuthorizationUtils {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationUtils.class);

    public static Map<String, Object> getUserInfo() {
        try {
            Map<String, Object> principal = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return principal;
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new RuntimeException("获取当前用户信息异常！");
        }
    }

}
