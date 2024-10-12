package com.nhnacademy.mini_dooray.gateway.task.adapter;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskRequest;
import com.nhnacademy.mini_dooray.gateway.task.domain.TaskResponse;
import com.nhnacademy.mini_dooray.gateway.task.exception.TaskGetException;
import com.nhnacademy.mini_dooray.gateway.task.exception.TaskNotFoundException;
import com.nhnacademy.mini_dooray.gateway.task.exception.TaskNotRegisterException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskAdapter {

    private final RestTemplate restTemplate;
    private final String TASK_API_URL = "http://localhost:8082/api";

    public TaskResponse getTask(Long projectId, Long taskId) {
        String uri = "/project/"+projectId+"/tasks/"+taskId;

        ResponseEntity<TaskResponse> response = restTemplate.getForEntity(TASK_API_URL + uri, TaskResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new TaskNotFoundException("task not found");

    }

    public List<TaskResponse> getTasks(Long projectId) {
        String uri = "/project/"+projectId+"/tasks";

        ResponseEntity<List<TaskResponse>> response = restTemplate.exchange(
                TASK_API_URL + uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TaskResponse>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new TaskGetException("GET error!");
    }

    public MessageDto createTask(Long projectId, TaskRegistrationRequest taskRegistrationRequest) {
        String uri = "/project/" + projectId + "/task";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TaskRegistrationRequest> entity = new HttpEntity<>(taskRegistrationRequest, httpHeaders);

        ResponseEntity<MessageDto> response = restTemplate.postForEntity(TASK_API_URL + uri, entity, MessageDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new TaskNotRegisterException("task not registered");
    }
}
