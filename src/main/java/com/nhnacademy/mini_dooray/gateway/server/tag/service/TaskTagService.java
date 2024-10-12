package com.nhnacademy.mini_dooray.gateway.server.tag.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TaskTagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagDeleteFailedException;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagRegistrationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTagService {

    private final Adapter adapter;
    private final String TASK_TAG_API_URL = "http://localhost:8082/api/project/";

    public MessageDto attachTag(Long projectId, Long taskId, TaskTagRegistrationRequest request){
        String uri = projectId + "/task/" + taskId + "/tag";

        ResponseEntity<MessageDto> response = adapter.post(TASK_TAG_API_URL + uri, request);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }
        throw new TagRegistrationFailedException("failed to attach tag");
    }

    public MessageDto detachTag(Long projectId, Long taskId, Long tagId){
        String uri = projectId + "/task/" + taskId + "/tag/" + tagId;
        ResponseEntity<MessageDto> response = adapter.delete(TASK_TAG_API_URL + uri);

        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }
        throw new TagDeleteFailedException("failed to detach tag");
    }
}
