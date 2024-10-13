package com.nhnacademy.mini_dooray.gateway.server.account.handler;


import com.nhnacademy.mini_dooray.gateway.common.util.CookieUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTemplate<String,Object> sessionRedisTemplate;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String HASH_NAME = "Session:";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie cookie = CookieUtils.getCookie(request, "SESSION");

        if (cookie != null) {
            sessionRedisTemplate.opsForHash().delete(HASH_NAME, cookie.getValue());
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
