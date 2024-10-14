package com.nhnacademy.mini_dooray.gateway.server.account.adapter;

import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginRequest;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.server.account.domain.MemberRegistrationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="memberSendClient", url = "http://localhost:8083/api")
public interface MemberAdapter {

    @PostMapping("/member")
    ResponseEntity<MessageDto> register(@RequestBody MemberRegistrationRequest memberRegistrationRequest);

    @GetMapping("/member/{userId}")
    ResponseEntity<MemberLoginResponse> getMember(@PathVariable("userId") String userId);

}
