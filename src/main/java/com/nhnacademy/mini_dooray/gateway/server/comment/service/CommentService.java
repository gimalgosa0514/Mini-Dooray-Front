package com.nhnacademy.mini_dooray.gateway.server.comment.service;

import com.nhnacademy.mini_dooray.gateway.server.adapter.CommentAdapter;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentAdapter adapter;


    public List<CommentResponse> getComments(Long projectId, Long taskId) {


        try {
            ResponseEntity<List<CommentResponse>> response = adapter.getComments(projectId,taskId);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            return new ArrayList<>();
        }
        catch (HttpClientErrorException | HttpServerErrorException e ) {
            return new ArrayList<>();
        }
    }

    public MessageDto createComment(Long projectId, Long taskId, CommentRegistrationRequest request){


        try {
            ResponseEntity<MessageDto> response = adapter.createComment(projectId,taskId,request);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch (HttpClientErrorException | HttpServerErrorException e ) {
            throw new CommentRegistrationFailedException("comment registration failed");
        }
        throw new CommentRegistrationFailedException("comment registration failed");
    }

    public MessageDto updateComment(Long projectId, Long taskId, Long commentId, CommentUpdateRequest request){


        try {
            ResponseEntity<MessageDto> response = adapter.updateComment(projectId,taskId,commentId, request);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch (HttpClientErrorException | HttpServerErrorException e ) {
            throw new CommentUpdateFailedException("comment update failed");
        }

        throw new CommentUpdateFailedException("comment update failed");
    }

    public MessageDto deleteComment(Long projectId, Long taskId, Long commentId){


        try {
            ResponseEntity<MessageDto> response = adapter.deleteComment(projectId,taskId,commentId);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        catch (HttpClientErrorException | HttpServerErrorException e ) {
            throw new CommentDeletedFailedException("comment deletion failed");
        }

        throw new CommentDeletedFailedException("comment delete failed");
    }
}
