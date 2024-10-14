package com.nhnacademy.mini_dooray.gateway.server.account.service;

import com.nhnacademy.mini_dooray.gateway.server.account.adapter.MemberAdapter;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.server.account.exception.MemberNotFoundException;
import com.nhnacademy.mini_dooray.gateway.server.account.exception.MemberRegisterFailedException;
import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final MemberAdapter adapter;

    public MessageDto register(MemberRegistrationRequest request) {



        try{
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            ResponseEntity<MessageDto> response =  adapter.register(request);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new MemberRegisterFailedException("member register failed");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MemberRegisterFailedException("member register failed");
        }

    }

    public MemberLoginResponse getMember(String userId){


        try{
            ResponseEntity<MemberLoginResponse> response = adapter.getMember(userId);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new MemberNotFoundException("member not found");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MemberNotFoundException("member not found");
        }

    }

}
