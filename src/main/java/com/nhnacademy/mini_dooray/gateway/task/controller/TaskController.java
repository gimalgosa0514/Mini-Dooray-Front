package com.nhnacademy.mini_dooray.gateway.task.controller;

import com.nhnacademy.mini_dooray.gateway.task.adapter.TaskAdapter;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskRequest;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskAdapter taskAdapter;

    @GetMapping("/project/{projectId}/task")
    public String getTask(@PathVariable Long projectId, Model model) {
        List<TaskResponse> responses = taskAdapter.getTasks(projectId);
        model.addAttribute("tasks", responses);

        return "projectTasks";
    }

    @GetMapping("/project/{projectId}/task/{taskId}")
    public String getTask(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        TaskResponse taskResponse = taskAdapter.getTask(projectId, taskId);
        model.addAttribute("task", taskResponse);
        return "projectTask";
    }

    @PostMapping("/project/{projectId}/task")
    public String postTask(@PathVariable Long projectId, @RequestBody TaskRegistrationRequest taskRegistrationRequest) {
        taskAdapter.createTask(projectId, taskRegistrationRequest);

        return "redirect:/project/" + projectId + "/task";
    }

//    @PutMapping("/project/{projectId}/task/{taskId}")
//    public String updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
//
//    }
}
