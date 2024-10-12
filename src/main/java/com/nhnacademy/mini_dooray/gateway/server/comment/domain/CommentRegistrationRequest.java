package com.nhnacademy.mini_dooray.gateway.server.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRegistrationRequest {
    private String memberId;
    private String commentContent;
}
