package com.nhnacademy.mini_dooray.gateway.milestone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MilestoneController {

    @GetMapping("/project/{projectId}/task/{taskId}/milestone")
    public String viewMilestone(@PathVariable String projectId, @PathVariable String taskId) {
        return "home";
    }
}
