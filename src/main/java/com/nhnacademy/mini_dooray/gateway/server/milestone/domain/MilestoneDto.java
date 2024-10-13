package com.nhnacademy.mini_dooray.gateway.server.milestone.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MilestoneDto {

    private String milestoneName;

    private LocalDateTime milestoneStartline;

    private LocalDateTime milestoneDeadline;


}
