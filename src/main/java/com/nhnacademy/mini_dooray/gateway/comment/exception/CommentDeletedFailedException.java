package com.nhnacademy.mini_dooray.gateway.comment.exception;

public class CommentDeletedFailedException extends RuntimeException {
    public CommentDeletedFailedException(String message) {
        super(message);
    }
}
