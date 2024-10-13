package com.nhnacademy.mini_dooray.gateway.server.task.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskResponse;
import com.nhnacademy.mini_dooray.gateway.server.task.domain.TaskUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.task.exception.TaskGetException;
import com.nhnacademy.mini_dooray.gateway.server.task.exception.TaskNotFoundException;
import com.nhnacademy.mini_dooray.gateway.server.task.exception.TaskNotRegisterException;
import com.nhnacademy.mini_dooray.gateway.server.task.exception.TaskNotUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final Adapter adapter;
    private final String URL = "http://localhost:8082/api";

    public TaskResponse getTask(Long projectId, Long taskId) {
        String uri = "/project/"+projectId+"/task/"+taskId;

        try {
            ResponseEntity<TaskResponse> response = adapter.get(URL + uri, TaskResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        }

        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new TaskNotFoundException("task not found");
        }
        throw new TaskNotFoundException("task not found");

    }
    public List<TaskResponse> getTasks(Long projectId) {
        String uri = "/project/"+projectId+"/task";

        try {
            ResponseEntity<List<TaskResponse>> response = adapter.getList(URL + uri, new ParameterizedTypeReference<List<TaskResponse>>() {});

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            return new ArrayList<>();
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ArrayList<>();
        }

    }
    public MessageDto createTask(Long projectId, TaskRegistrationRequest taskRegistrationRequest) {
        String uri = "/project/" + projectId + "/task";

        try {
            ResponseEntity<MessageDto> response = adapter.post(URL+uri, taskRegistrationRequest);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch ( HttpClientErrorException | HttpServerErrorException e) {

            throw new TaskNotRegisterException("task not registered");

        }

        throw new TaskNotRegisterException("task not registered");
    }


    public MessageDto updateTask(Long projectId, Long taskId, TaskUpdateRequest taskUpdateRequest) {
        String uri = "/project/" + projectId + "/task" + taskId;

        try {
            ResponseEntity<MessageDto> response = adapter.put(URL + uri, taskUpdateRequest);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new TaskNotUpdateException("task updated error");
        }

        throw new TaskNotUpdateException("task updated error");
    }

    public MessageDto deleteTask(Long projectId, Long taskId) {
        String uri = "/project/" + projectId + "/task/" + taskId;

        try {
            ResponseEntity<MessageDto> response = adapter.delete(URL + uri);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch ( HttpClientErrorException | HttpServerErrorException e) {
            throw new TaskNotFoundException("task not found");
        }

        throw new TaskNotFoundException("task not found");
    }
}
