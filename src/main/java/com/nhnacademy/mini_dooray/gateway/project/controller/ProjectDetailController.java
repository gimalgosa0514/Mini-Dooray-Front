package com.nhnacademy.mini_dooray.gateway.project.controller;

import com.nhnacademy.mini_dooray.gateway.project.adapter.ProjectAdapter;
import com.nhnacademy.mini_dooray.gateway.project.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProjectDetailController {

    private final ProjectAdapter projectAdapter;

    @GetMapping("/project/{projectId}")
    public ModelAndView viewProjectDetail(@PathVariable String projectId) {
        ModelAndView mav = new ModelAndView("projectDetail");
        mav.addObject("projectId", projectId);
        //TODO: api와 연결 후 실행 시 주석 해제
//        mav.addObject("project", projectAdapter.getProject(projectId));
        return mav;
    }
}
