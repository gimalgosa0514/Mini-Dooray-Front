package com.nhnacademy.mini_dooray.gateway.project.controller;

import com.nhnacademy.mini_dooray.gateway.project.adapter.ProjectAdapter;
import com.nhnacademy.mini_dooray.gateway.project.domain.Project;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProjectCreateController {

    private final ProjectAdapter projectAdapter;

    @GetMapping("/project/create")
    public String viewCreateProject(@RequestParam(required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "projectCreate";
    }

    @PostMapping("/project/create")
    public String saveProject(@ModelAttribute ProjectCreateRequest projectCreateRequest, Model model) {

        //TODO: api와 연결 후 실행 시 주석 해제
        //        projectAdapter.createProject(projectCreateRequest);
        return "redirect:/"+projectCreateRequest.getMemberId()+"/project";
    }


}
