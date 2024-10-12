package com.nhnacademy.mini_dooray.gateway.account.service;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.account.exception.LoginFailedException;
import com.nhnacademy.mini_dooray.gateway.account.exception.MemberRegisterFailedException;
import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//TODO 비밀번호 암호화
public class AccountService {

    private final String ACCOUNT_API_URL = "http://localhost:8081";
    private final Adapter adapter;

    public MessageDto register(MemberRegistrationRequest request) {

        String uri = "/register";
        ResponseEntity<MessageDto> response =  adapter.post(ACCOUNT_API_URL + uri, request);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new MemberRegisterFailedException("member register failed");
    }

    public MemberLoginResponse doLogin(String userId){
        String uri = "/member/" + userId;

        ResponseEntity<MemberLoginResponse> response = restTemplate.getForEntity(ACCOUNT_API_URL + uri, MemberLoginResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new LoginFailedException("login failed");
    }

}
