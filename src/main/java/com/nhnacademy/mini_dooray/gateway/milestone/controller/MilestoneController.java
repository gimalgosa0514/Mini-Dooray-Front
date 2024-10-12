package com.nhnacademy.mini_dooray.gateway.milestone.controller;

import com.nhnacademy.mini_dooray.gateway.milestone.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.gateway.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/project/{projectId}/task/{taskId}/milestone")
    public String milestone(@PathVariable String projectId, @PathVariable String taskId, Model model) {
        MilestoneDto response = milestoneService.getMilestone(projectId,taskId);
        model.addAttribute("milestone", response);
        return "taskMilestone";
    }

    @DeleteMapping("/project/{projectId}/task/{taskId}/milestone")
    public String deleteMilestone(@PathVariable String projectId, @PathVariable String taskId, Model model) {

        milestoneService.deleteMilestone(projectId,taskId);
        return "redirect:/project/"+projectId+"/task/"+taskId;
    }
}
