package com.nhnacademy.mini_dooray.gateway.server.account.filter;

import com.nhnacademy.mini_dooray.gateway.common.util.CookieUtils;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.server.account.service.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String,Object> sessionRedisTemplate;
    private final String HASH_MAP_NAME = "Session:";

    private final AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth != null && existingAuth.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie cookie = CookieUtils.getCookie(request, "SESSION");
        if (cookie == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String sessionId = cookie.getValue();

        if (sessionId != null) {
            Object o = sessionRedisTemplate.opsForHash().get(HASH_MAP_NAME, sessionId);
            String memberId = (String) o;
            MemberLoginResponse member = null;

            member = accountService.doLogin(memberId);

            if (o != null) {
                UserDetails user = new User(member.getId(), member.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER")));
                Authentication authentication = new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities());
                authentication.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request,response);
    }
}