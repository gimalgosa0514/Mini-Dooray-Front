package com.nhnacademy.mini_dooray.gateway.task.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskResponse {
    private String taskTitle;
    private String memberId;
    private String taskContent;
    private String milestoneName;
    private LocalDateTime milestoneStartLine;
    private LocalDateTime milestoneEndLine;
    private String tagName;
}
