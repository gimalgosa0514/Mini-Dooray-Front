package com.nhnacademy.mini_dooray.gateway.server.tag.controller;

import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TaskTagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.service.TaskTagService;
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

}
