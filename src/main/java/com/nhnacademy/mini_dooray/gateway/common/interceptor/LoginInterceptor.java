package com.nhnacademy.mini_dooray.gateway.common.interceptor;

import com.nhnacademy.mini_dooray.gateway.common.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = AuthUtil.getAuthentication();
        if (authentication != null) {
            response.sendRedirect("/project");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
