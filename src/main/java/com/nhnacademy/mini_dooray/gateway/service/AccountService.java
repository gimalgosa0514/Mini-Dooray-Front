package com.nhnacademy.mini_dooray.gateway.service;

import com.nhnacademy.mini_dooray.gateway.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.domain.MemberRegistrationResponse;
import com.nhnacademy.mini_dooray.gateway.exception.MemberRegisterException;
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
        throw new MemberRegisterException("member register failed");
    }
}
