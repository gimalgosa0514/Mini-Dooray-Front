package com.nhnacademy.mini_dooray.gateway.account.service;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationResponse;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.account.exception.LoginFailedException;
import com.nhnacademy.mini_dooray.gateway.account.exception.MemberRegisterFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final String ACCOUNT_API_URL = "http://localhost:8081";
    private final RestTemplate restTemplate;

    public MemberRegistrationResponse register(MemberRegistrationRequest request) {

        String uri = "/register";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MemberRegistrationRequest> entity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<MemberRegistrationResponse> response = restTemplate.postForEntity(ACCOUNT_API_URL + uri, entity, MemberRegistrationResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new MemberRegisterFailedException(response.getBody().getMessage());
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
