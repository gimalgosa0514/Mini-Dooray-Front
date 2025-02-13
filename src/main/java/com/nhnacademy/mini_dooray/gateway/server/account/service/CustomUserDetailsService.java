package com.nhnacademy.mini_dooray.gateway.server.account.service;

import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberLoginResponse memberLoginResponse = accountService.getMember(username);

        if (Objects.isNull(memberLoginResponse)) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(memberLoginResponse.getId(), memberLoginResponse.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}
