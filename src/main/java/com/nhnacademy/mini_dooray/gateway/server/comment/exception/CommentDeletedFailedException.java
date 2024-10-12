package com.nhnacademy.mini_dooray.gateway.server.comment.exception;

public class CommentDeletedFailedException extends RuntimeException {
    public CommentDeletedFailedException(String message) {
        super(message);
    }
}
