package com.nhnacademy.mini_dooray.gateway.server.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskRegistrationRequest {
    private String taskTitle;
    private String memberId;
    private String taskContent;
    private long projectId;
}