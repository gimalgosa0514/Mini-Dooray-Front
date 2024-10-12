package com.nhnacademy.mini_dooray.gateway.server.milestone.controller;

import com.nhnacademy.mini_dooray.gateway.server.milestone.domain.MilestoneAttachRequest;
import com.nhnacademy.mini_dooray.gateway.server.milestone.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.gateway.server.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/project/{projectId}/task/{taskId}/milestone")
    public String attachMilestone(@PathVariable String projectId, @PathVariable String taskId, @ModelAttribute MilestoneAttachRequest milestoneAttachRequest) {

        milestoneService.attachMilestone(projectId,taskId,milestoneAttachRequest);
        return "/redirect:/project/"+projectId+"/task/"+taskId;
    }

    @GetMapping("/project/{projectId}/milestone")
    public String milestoneList(@PathVariable Long projectId,Model model) {

        milestoneService.getMilestones(projectId);
        model.addAttribute("milestones", milestoneService.getMilestones(projectId));
        return "milestoneList";
    }

    @PutMapping("/project/{projectId}/milestone/{milestoneId}")
    public String reviseMilestone(@PathVariable String projectId, @PathVariable String milestoneId, @ModelAttribute MilestoneDto milestoneDto) {

        milestoneService.reviseMilestone(projectId,milestoneId,milestoneDto);
        return "redirect:/project/"+projectId;
    }

    @DeleteMapping("/project/{projectId}/milestone/{milestoneId}")
    public String deleteMilestone(@PathVariable String projectId, @PathVariable String milestoneId) {
        milestoneService.deleteMilestoneFromProject(projectId,milestoneId);
        return "redirect:/project/"+projectId;
    }


}
