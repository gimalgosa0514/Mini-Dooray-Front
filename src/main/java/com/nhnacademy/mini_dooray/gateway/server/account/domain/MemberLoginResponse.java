package com.nhnacademy.mini_dooray.gateway.server.account.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResponse {
    private String id;
    private String password;
}