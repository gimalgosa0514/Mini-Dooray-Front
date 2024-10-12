package com.nhnacademy.mini_dooray.gateway.server.account.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RedisTemplate<String,Object> sessionRedisTemplate;

    private final String HASH_NAME = "Session:";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        String sessionId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200);
        cookie.setPath("/");
        response.addCookie(cookie);
        sessionRedisTemplate.opsForHash().put(HASH_NAME,"sessionId",sessionId);
        super.onAuthenticationSuccess(request, response, authentication);

    }
}
