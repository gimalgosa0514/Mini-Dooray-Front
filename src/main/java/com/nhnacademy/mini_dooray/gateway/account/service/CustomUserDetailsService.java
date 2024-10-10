package com.nhnacademy.mini_dooray.gateway.account.service;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
@Component
//TODO : 추후 확인 필요
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberLoginResponse memberLoginResponse = accountService.doLogin(username);

        if (Objects.isNull(memberLoginResponse)) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(memberLoginResponse.getId(), memberLoginResponse.getPassword(), new ArrayList<>());
    }
}
