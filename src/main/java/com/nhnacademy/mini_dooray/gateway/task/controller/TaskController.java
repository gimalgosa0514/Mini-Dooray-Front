package com.nhnacademy.mini_dooray.gateway.task.controller;

import com.nhnacademy.mini_dooray.gateway.task.adapter.TaskAdapter;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskResponse;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskUpdateRequest;
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

    @PutMapping("/project/{projectId}/task/{taskId}")
    public String updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        taskAdapter.updateTask(projectId, taskId, taskUpdateRequest);

        return "redirect:/project/" + projectId + "/task";
    }

    @DeleteMapping("/project/{projectId}/task/{taskId}")
    public String deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskAdapter.deleteTask(projectId, taskId);

        return "redirect:/project/" + projectId + "/task";
    }

}
