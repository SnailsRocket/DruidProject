package com.xubo.druid.handler;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.entity.vo.LoginUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author xubo
 * @Date 2022/5/17 17:12
 */
public class LoginInterceper implements HandlerInterceptor {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        LoginUser loginUserVo = new LoginUser();
        if(ObjectUtil.isEmpty(loginUser)) {
            loginUserVo.setId(loginUser.getId());
        }
        Cookie[] cookies = request.getCookies();
        if(ObjectUtils.isNotEmpty(cookies) || cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("user-key")) {
                    loginUserVo.setPersonKey(cookie.getValue());
                    loginUserVo.setTempUser(true);
                }
            }
        }

        if(StringUtils.isEmpty(loginUserVo.getPersonKey())) {
            loginUserVo.setPersonKey(UUID.randomUUID().toString());
        }

        threadLocal.set(loginUserVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LoginUser loginUser = threadLocal.get();
        // 如果浏览器中没有user-key
        if(!loginUser.getTempUser()) {
            Cookie cookie = new Cookie("user-key", loginUser.getPersonKey());
            cookie.setDomain("snailsRocket.com");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
