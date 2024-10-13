package com.nhnacademy.mini_dooray.gateway.server.account.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
