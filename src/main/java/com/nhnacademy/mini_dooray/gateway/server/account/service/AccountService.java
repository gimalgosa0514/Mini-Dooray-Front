package com.nhnacademy.mini_dooray.gateway.server.account.service;

import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.server.account.exception.LoginFailedException;
import com.nhnacademy.mini_dooray.gateway.server.account.exception.MemberRegisterFailedException;
import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final String ACCOUNT_API_URL = "http://localhost:8081";
    private final PasswordEncoder passwordEncoder;
    private final Adapter adapter;

    public MessageDto register(MemberRegistrationRequest request) {

        String uri = "/member";

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        ResponseEntity<MessageDto> response =  adapter.post(ACCOUNT_API_URL + uri, request);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new MemberRegisterFailedException("member register failed");
    }

    public MemberLoginResponse doLogin(String userId){
        String uri = "/member/" + userId;

        ResponseEntity<MemberLoginResponse> response = adapter.get(ACCOUNT_API_URL + uri, MemberLoginResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new LoginFailedException("login failed");
    }

}
