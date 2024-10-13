package com.nhnacademy.mini_dooray.gateway.server.comment.controller;

import com.nhnacademy.mini_dooray.gateway.common.util.AuthUtil;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.comment.domain.CommentUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/project/{projectId}/task/{taskId}/comment")
    public String comments(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        model.addAttribute("comments", commentService.getComments(projectId, taskId));

        return "comments";
    }

    @PutMapping("/project/{projectId}/task/{taskId}/comment/{commentId}")
    public String updateComment(@PathVariable Long projectId
            , @PathVariable Long taskId
            , @PathVariable Long commentId
            , @ModelAttribute CommentUpdateRequest request) {
        commentService.updateComment(projectId, taskId, commentId, request);

        return "redirect:/project/" + projectId + "/task/" + taskId;
    }

    @DeleteMapping("/project/{projectId}/task/{taskId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long projectId
            , @PathVariable Long taskId
            , @PathVariable Long commentId) {

        commentService.deleteComment(projectId, taskId, commentId);
        return "redirect:/project/" + projectId + "/task/" + taskId;
    }
    @PostMapping("/project/{projectId}/task/{taskId}/comment")
    public String addComment(@PathVariable Long projectId
            , @PathVariable Long taskId
            , @ModelAttribute CommentRegistrationRequest request) {

        request.setMemberId(AuthUtil.getMemberId());
        request.setTaskId(taskId);
        commentService.createComment(projectId, taskId, request);
        return "redirect:/project/" + projectId + "/task/" + taskId;
    }
}
