package com.nhnacademy.mini_dooray.gateway.server.tag.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagResponse;
import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagDeleteFailedException;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagRegistrationFailedException;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagUpdateFailedException;
import com.nhnacademy.mini_dooray.gateway.server.tag.exception.TagsGetFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final Adapter adapter;
    private final String TAG_API_URL = "http://localhost:8083/api/project/";

    public List<TagResponse> getTags(Long projectId) {
        String uri = projectId+"/tag";

        try{
            ResponseEntity<List<TagResponse>> response = adapter.getList(TAG_API_URL + uri, new ParameterizedTypeReference<List<TagResponse>>() {});

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            return new ArrayList<>();
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ArrayList<>();
        }

    }

    public MessageDto createTag(Long projectId, TagRegistrationRequest request){
        String uri = projectId+"/tag";

        try{
            ResponseEntity<MessageDto> response = adapter.post(TAG_API_URL + uri, request);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new TagRegistrationFailedException("tag registration failed");
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new TagRegistrationFailedException("tag registration failed");
        }

    }
    public MessageDto updateTag(Long projectId, Long tagId, TagUpdateRequest request){
        String uri = projectId+"/tag/" + tagId;

        try{
            ResponseEntity<MessageDto> response = adapter.put(TAG_API_URL + uri, request);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new TagUpdateFailedException("tag update failed");
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new TagUpdateFailedException("tag update failed");
        }

    }

    public MessageDto deleteTag(Long projectId, Long tagId) {
        String uri = projectId+"/tag/" + tagId;

        try{
            ResponseEntity<MessageDto> response = adapter.delete(TAG_API_URL + uri);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new TagDeleteFailedException("tag delete failed");
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new TagDeleteFailedException("tag delete failed");
        }

    }
}
