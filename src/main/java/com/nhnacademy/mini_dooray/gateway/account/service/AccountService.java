package com.nhnacademy.mini_dooray.gateway.account.service;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationResponse;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.account.exception.LoginFailedException;
import com.nhnacademy.mini_dooray.gateway.account.exception.MemberRegisterFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//TODO#1 api url 추가
public class AccountService {

    @Autowired
    private RestTemplate restTemplate;

    public MemberRegistrationResponse register(MemberRegistrationRequest request) {

        String url = "";

        ResponseEntity<MemberRegistrationResponse> response = restTemplate.postForEntity(url, request, MemberRegistrationResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new MemberRegisterFailedException(response.getBody().getMessage());
    }

    public MemberLoginResponse doLogin(String userId){
        String url = "";

        ResponseEntity<MemberLoginResponse> response = restTemplate.postForEntity(url, userId, MemberLoginResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new LoginFailedException("login failed");
    }

}
