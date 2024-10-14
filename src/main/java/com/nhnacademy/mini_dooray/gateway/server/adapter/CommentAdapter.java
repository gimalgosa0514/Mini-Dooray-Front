package com.nhnacademy.mini_dooray.gateway.server.adapter;

import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentResponse;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="commentSenderClient", url = "http://localhost:8083/api")
public interface CommentAdapter {



    @GetMapping("/project/{projectId}/task/{taskId}/comment")
    ResponseEntity<List<CommentResponse>> getComments(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId);


    @PostMapping("/project/{projectId}/task/{taskId}/comment")
    ResponseEntity<MessageDto> createComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @RequestBody CommentRegistrationRequest request);


    @PutMapping("/project/{projectId}/task/{taskId}/comment/{commentId}")
    ResponseEntity<MessageDto> updateComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId,@PathVariable("commentId") Long commentId, @RequestBody CommentUpdateRequest request);

    @DeleteMapping("/project/{projectId}/task/{taskId}/comment/{commentId}")
    ResponseEntity<MessageDto> deleteComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @PathVariable("commentId") Long commentId);
}
