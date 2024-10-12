package com.nhnacademy.mini_dooray.gateway.server.account.exception;

public class MemberRegisterFailedException extends RuntimeException {
    public MemberRegisterFailedException(String message) {
        super(message);
    }
}
