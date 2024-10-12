package com.nhnacademy.mini_dooray.gateway.project.controller;

import com.nhnacademy.mini_dooray.gateway.project.adapter.ProjectAdapter;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectAdapter projectAdapter;



    // 멤버가 속한 프로젝트 목록
    @GetMapping("/{memberId}/project")
    public ModelAndView viewProject(@PathVariable String memberId) {
        ModelAndView mav = new ModelAndView("project");
        mav.addObject("memberId", memberId);
        //TODO: api와 연결 후 실행 시 주석 해제
        //mav.addObject("projects",projectAdapter.projectList());

        return mav;
    }

    // 프로젝트 생성하는 부분
    @GetMapping("/project/create")
    public String viewCreateProject(@RequestParam(required = false) String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "projectCreate";
    }

    // 프로젝트 생성 포스트 부분
    @PostMapping("/project/create")
    public String saveProject(@ModelAttribute ProjectCreateRequest projectCreateRequest, Model model) {

        //TODO: api와 연결 후 실행 시 주석 해제
        //        projectAdapter.createProject(projectCreateRequest);
        return "redirect:/"+projectCreateRequest.getMemberId()+"/project";
    }

    // 프로젝트 상세보기
    @GetMapping("/project/{projectId}")
    public ModelAndView viewProjectDetail(@PathVariable String projectId) {
        ModelAndView mav = new ModelAndView("projectDetail");
        //TODO: api와 연결 후 실행 시 주석 해제
//        mav.addObject("project", projectAdapter.getProject(projectId));
        mav.addObject("project", new ProjectDetailResponse());
        return mav;
    }

//    @GetMapping("/project/{projectId}/member")
//    public ModelAndView viewProjectMember(@PathVariable String projectId) {
//        ModelAndView mav = new ModelAndView("projectMember");
//
//    }


}
