package com.nhnacademy.mini_dooray.gateway.account.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResponse {
    private String id;
    private String password;
}