package com.nhnacademy.mini_dooray.gateway.server.tag.controller;

import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.domain.TagUpdateRequest;
import com.nhnacademy.mini_dooray.gateway.server.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/project/{projectId}/tag/create")
    public String createTag(Model model, @PathVariable Long projectId) {
        model.addAttribute("projectId", projectId);
        return "tagCreate";
    }

    @GetMapping("/project/{projectId}/tag")
    public String getTagList(@PathVariable Long projectId, Model model) {
        model.addAttribute("tags", tagService.getTags(projectId));
        return "tagList";
    }

    @PostMapping("/project/{projectId}/tag")
    public String createTag(@PathVariable Long projectId, @ModelAttribute TagRegistrationRequest tagRegistrationRequest) {
        tagService.createTag(projectId, tagRegistrationRequest);
        return "redirect:/project/" + projectId;
    }

    @PutMapping("/project/{projectId}/tag/{tagId}")
    public String updateTag(@PathVariable Long projectId, @PathVariable Long tagId, @ModelAttribute TagUpdateRequest tagRequest) {
        tagService.updateTag(projectId, tagId, tagRequest);
        return "redirect:/project/" + projectId;
    }

    @DeleteMapping("/project/{projectId}/tag/{tagId}")
    public String deleteTag(@PathVariable Long projectId, @PathVariable Long tagId) {
        tagService.deleteTag(projectId, tagId);
        return "redirect:/project/" + projectId;
    }
}
