package com.nhnacademy.mini_dooray.gateway.server.project.controller;

import com.nhnacademy.mini_dooray.gateway.server.account.service.AccountService;
import com.nhnacademy.mini_dooray.gateway.server.milestone.service.MilestoneService;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.Project;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectMemberAddRequest;
import com.nhnacademy.mini_dooray.gateway.server.project.service.ProjectService;
import com.nhnacademy.mini_dooray.gateway.common.util.AuthUtil;
import com.nhnacademy.mini_dooray.gateway.server.tag.service.TagService;
import com.nhnacademy.mini_dooray.gateway.server.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final MilestoneService milestoneService;
    private final TagService tagService;
    private final AccountService accountService;

    // 멤버가 속한 프로젝트 목록
    @GetMapping("/project")
    public ModelAndView viewProject() {
        ModelAndView mav = new ModelAndView("project");
        String memberId = AuthUtil.getMemberId();
        mav.addObject("memberId", memberId);

        List<Project> projects = projectService.getProjects(memberId);

        mav.addObject("projects",projects);

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
        projectCreateRequest.setMemberId(AuthUtil.getMemberId());
        //TODO: api와 연결 후 실행 시 주석 해제
        projectService.createProject(projectCreateRequest);
        return "redirect:/project";
    }

    // 프로젝트 상세보기
    @GetMapping("/project/{projectId}")
    public ModelAndView viewProjectDetail(@PathVariable Long projectId) {
        ModelAndView mav = new ModelAndView("projectDetail");
        //TODO: api와 연결 후 실행 시 주석 해제
        mav.addObject("project", projectService.getProjectDetail(projectId));
        mav.addObject("taskList", taskService.getTasks(projectId));
        mav.addObject("milestoneList", milestoneService.getMilestones(projectId));
        mav.addObject("tagList",tagService.getTags(projectId));
        mav.addObject("memberList");
        return mav;
    }

    @GetMapping("/project/{projectId}/member")
    public ModelAndView viewProjectMember(@PathVariable Long projectId) {
        ModelAndView mav = new ModelAndView("projectAddMember");
        mav.addObject("projectId", projectId);
        return mav;
    }

    @PostMapping("project/{projectId}/member")
    public String addProjectMember(@PathVariable String projectId,
                                   @ModelAttribute ProjectMemberAddRequest projectMemberAddRequest) {

        // 데이터를 주고 받은 후 넘겨 받아야 함.
        accountService.getMember(projectMemberAddRequest.getMemberId());

        projectService.addProjectMember(projectId,projectMemberAddRequest);
        return "redirect:/project/"+projectId;
    }

}
