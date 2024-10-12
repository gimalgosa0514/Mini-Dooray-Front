package com.nhnacademy.mini_dooray.gateway.tag.controller;

import com.nhnacademy.mini_dooray.gateway.tag.domain.TagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.tag.domain.TagUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/project/{projectId}/tag")
    public String getTagList(@PathVariable Long projectId, Model model) {
        model.addAttribute("tags", tagService.getTags(projectId));
        return "tagList";
    }

    @PostMapping("/project/{projectId}/tag")
    public String createTag(@PathVariable Long projectId, @RequestBody TagRegistrationRequest tagRequest) {
        tagService.createTag(projectId, tagRequest);
        return "redirect:/project/" + projectId;
    }

    @PutMapping("/project/{projectId}/tag/{tagId}")
    public String updateTag(@PathVariable Long projectId, @PathVariable Long tagId, @RequestBody TagUpdateRequest tagRequest) {
        tagService.updateTag(projectId, tagId, tagRequest);
        return "redirect:/project/" + projectId;
    }

    @DeleteMapping("/project/{projectId}/tag/{tagId}")
    public String deleteTag(@PathVariable Long projectId, @PathVariable Long tagId) {
        tagService.deleteTag(projectId, tagId);
        return "redirect:/project/" + projectId;
    }
}
