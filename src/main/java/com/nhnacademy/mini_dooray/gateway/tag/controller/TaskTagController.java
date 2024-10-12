package com.nhnacademy.mini_dooray.gateway.tag.controller;

import com.nhnacademy.mini_dooray.gateway.tag.domain.TaskTagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.tag.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskTagController {

    private final TaskTagService taskTagService;

    @PostMapping("/project/{projectId}/task/{taskId}/tag")
    public String attachTag(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskTagRegistrationRequest request) {
        taskTagService.attachTag(projectId, taskId, request);

        return "redirect:/project/" + projectId + "/task/" + taskId;
    }

    @DeleteMapping("/project/{projectId}/task/{taskId}/tag/{tagId}")
    public String detachTag(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long tagId) {
        taskTagService.detachTag(projectId, taskId, tagId);

    }
}
