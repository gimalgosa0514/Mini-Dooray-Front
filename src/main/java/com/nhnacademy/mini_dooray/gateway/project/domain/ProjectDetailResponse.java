package com.nhnacademy.mini_dooray.gateway.project.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDetailResponse {

    private String projectName;
    private String projectStatus;
    private String projectManagerId;
    private List<Object> memberList; // 프로젝트에 등록된 멤버 리스트
    private List<Object> taskList; //프로젝트에 등록된 업무 목록들

}
