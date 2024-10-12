package com.nhnacademy.mini_dooray.gateway.task.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateRequest {
    private String taskTitle;
    private String taskContent;
}
