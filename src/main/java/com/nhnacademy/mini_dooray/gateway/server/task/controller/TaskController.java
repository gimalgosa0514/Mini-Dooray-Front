package com.nhnacademy.mini_dooray.gateway.server.task.controller;

import com.nhnacademy.mini_dooray.gateway.common.util.AuthUtil;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentResponse;
import com.nhnacademy.mini_dooray.gateway.server.comment.service.CommentService;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskResponse;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping("/project/{projectId}/task")
    public String getTask(@PathVariable Long projectId, Model model) {
        List<TaskResponse> responses = taskService.getTasks(projectId);
        model.addAttribute("taskList", responses);

        return "projectDetail";
    }

    @GetMapping("/project/{projectId}/task/{taskId}")
    public String getTask(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        TaskResponse taskResponse = taskService.getTask(projectId, taskId);
        List<CommentResponse> comments = commentService.getComments(projectId, taskId);
        model.addAttribute("comments", comments);
        model.addAttribute("task", taskResponse);
        model.addAttribute("projectId", projectId);
        return "projectTask";
    }

    @GetMapping("/project/{projectId}/task/create")
    public String createTask(@PathVariable Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "taskCreate";
    }

    @PostMapping("/project/{projectId}/task/create")
    public String postTask(@PathVariable Long projectId, @ModelAttribute TaskRegistrationRequest taskRegistrationRequest) {
        String memberId= AuthUtil.getMemberId();
        taskRegistrationRequest.setMemberId(memberId);
        taskService.createTask(projectId, taskRegistrationRequest);

        return "redirect:/project/" + projectId;
    }

    @GetMapping("/project/{projectId}/task/{taskId}/delete")
    public String deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);

        return "redirect:/project/" + projectId;
    }


}
