package com.xubo.druid.handler;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.xubo.druid.entity.domain.Student;
import com.xubo.druid.entity.vo.LoginUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author xubo
 * @Date 2022/5/17 17:12
 * 手动实现拦截器
 * 抽象类 HandlerInterceptorAdapter 也继承了 HandlerInterceptor 接口
 * 参考博客：https://blog.csdn.net/weixin_44259720/article/details/104615086
 * 日志记录  可以记录请求信息的日志，以便进行信息监控、信息统计等
 * 权限校验  如登陆检测，进入处理器检测是否登陆，如果没有直接返回到登陆页面。
 * 性能监控  典型的是慢日志。
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
