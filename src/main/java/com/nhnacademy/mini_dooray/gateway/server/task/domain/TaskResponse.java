package com.nhnacademy.mini_dooray.gateway.server.task.domain;

import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private long taskId;
    private String taskTitle;
    private String memberId;
    private String taskContent;
    private String milestoneName;
    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;
    private List<TagResponse> tagName;
}
