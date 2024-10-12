package com.nhnacademy.mini_dooray.gateway.server.account.exception;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException(String message) {
        super(message);
    }
}
