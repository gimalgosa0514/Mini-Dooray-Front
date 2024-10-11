package com.nhnacademy.mini_dooray.gateway.project.controller;

import com.nhnacademy.mini_dooray.gateway.project.adapter.ProjectAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectAdapter projectAdapter;

    @GetMapping("/{memberId}/project")
    public ModelAndView viewProject(@PathVariable String memberId) {
        ModelAndView mav = new ModelAndView("project");
        mav.addObject("memberId", memberId);
        //TODO: api와 연결 후 실행 시 주석 해제
        //mav.addObject("projects",projectAdapter.projectList());

        return mav;
    }



}
