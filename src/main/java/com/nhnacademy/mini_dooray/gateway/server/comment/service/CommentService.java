package com.nhnacademy.mini_dooray.gateway.server.comment.service;

import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentResponse;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.comment.exception.CommentDeletedFailedException;
import com.nhnacademy.mini_dooray.gateway.server.comment.exception.CommentGetException;
import com.nhnacademy.mini_dooray.gateway.server.comment.exception.CommentRegistrationFailedException;
import com.nhnacademy.mini_dooray.gateway.server.comment.exception.CommentUpdateFailedException;
import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final Adapter adapter;
    private final String API_COMMENT_URL = "http://localhost:8082";

    public List<CommentResponse> getComments(Long projectId, Long taskId) {
        String uri = "/project/" + projectId + "/task/" + taskId + "/comment";
        ResponseEntity<List<CommentResponse>> response = adapter.getList(API_COMMENT_URL + uri, new ParameterizedTypeReference<List<CommentResponse>>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new CommentGetException("GET comments failed");
    }

    public MessageDto createComment(Long projectId, Long taskId, CommentRegistrationRequest request){
        String uri = "/project/" + projectId + "/task/" + taskId + "/comment";
        ResponseEntity<MessageDto> response = adapter.post(API_COMMENT_URL + uri, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new CommentRegistrationFailedException("comment registration failed");
    }

    public MessageDto updateComment(Long projectId, Long taskId, Long commentId, CommentUpdateRequest request){
        String uri = "/project/" + projectId + "/task/" + taskId + "/comment/" + commentId;

        ResponseEntity<MessageDto> response = adapter.put(API_COMMENT_URL + uri, request);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new CommentUpdateFailedException("comment update failed");
    }

    public MessageDto deleteComment(Long projectId, Long taskId, Long commentId){
        String uri = "/project/" + projectId + "/task/" + taskId + "/comment/" + commentId;
        ResponseEntity<MessageDto> response = adapter.delete(API_COMMENT_URL + uri);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new CommentDeletedFailedException("comment delete failed");
    }
}
