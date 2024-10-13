package com.nhnacademy.mini_dooray.gateway.server.milestone.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MilestoneListResponse {

    private long milestoneId;
    private String milestoneName;
    private LocalDateTime milestoneStartline;
    private LocalDateTime milestoneDeadline;
}
