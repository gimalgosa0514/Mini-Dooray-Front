package com.nhnacademy.mini_dooray.gateway.tag.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.tag.domain.TagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.tag.domain.TagResponse;
import com.nhnacademy.mini_dooray.gateway.tag.domain.TagUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.tag.exception.TagDeleteFailedException;
import com.nhnacademy.mini_dooray.gateway.tag.exception.TagRegistrationFailedException;
import com.nhnacademy.mini_dooray.gateway.tag.exception.TagUpdateFailedException;
import com.nhnacademy.mini_dooray.gateway.tag.exception.TagsGetFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final Adapter adapter;
    private final String TAG_API_URL = "http://localhost:8082/api/project/";

    public List<TagResponse> getTags(Long projectId) {
        String uri = projectId+"/tag";

        ResponseEntity<List<TagResponse>> response = adapter.getList(TAG_API_URL + uri, new ParameterizedTypeReference<List<TagResponse>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new TagsGetFailedException("get tag list failed");
    }

    public MessageDto createTag(Long projectId, TagRegistrationRequest request){
        String uri = projectId+"/tag";

        ResponseEntity<MessageDto> response = adapter.post(TAG_API_URL + uri, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new TagRegistrationFailedException("tag registration failed");
    }
    public MessageDto updateTag(Long projectId, Long tagId, TagUpdateRequest request){
        String uri = projectId+"/tag/" + tagId;

        ResponseEntity<MessageDto> response = adapter.put(TAG_API_URL + uri, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new TagUpdateFailedException("tag update failed");
    }

    public MessageDto deleteTag(Long projectId, Long tagId) {
        String uri = projectId+"/tag/" + tagId;

        ResponseEntity<MessageDto> response = adapter.delete(TAG_API_URL + uri);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new TagDeleteFailedException("tag delete failed");
    }
}
