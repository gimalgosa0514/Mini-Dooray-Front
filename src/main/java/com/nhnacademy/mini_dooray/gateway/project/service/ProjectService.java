package com.nhnacademy.mini_dooray.gateway.project.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.project.domain.*;
import com.nhnacademy.mini_dooray.gateway.project.exception.ProjectGetException;
import com.nhnacademy.mini_dooray.gateway.project.exception.ProjectMemberNotRegisterException;
import com.nhnacademy.mini_dooray.gateway.project.exception.ProjectNotFoundException;
import com.nhnacademy.mini_dooray.gateway.project.exception.ProjectNotRegisterException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final Adapter adapter;
    private final String URL = "http://localhost:8082/api";

    public List<ProjectDetailResponse> getProjects(String memberId) {
        String url = URL+"/project/member"+memberId;

        ResponseEntity<List<ProjectDetailResponse>> response = adapter.getList(url, new ParameterizedTypeReference<List<ProjectDetailResponse>>(){});

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new ProjectGetException("GET error!");
    }

    public ProjectDetailResponse getProjectDetail(String projectId) {
        String url = URL+"/project/"+projectId;

        ResponseEntity<ProjectDetailResponse> response = adapter.get(url,ProjectDetailResponse.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new ProjectNotFoundException("project not found");
    }

    public MessageDto createProject(ProjectCreateRequest request) {
        String url = URL+"/project";

        ResponseEntity<MessageDto> response = adapter.post(url,request);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new ProjectNotRegisterException("project not register");
    }

    public MessageDto addProjectMember(String projectId, ProjectMemberAddRequest projectMemberAddRequest) {
        String url = URL+"/project/"+projectId+"member";


        ResponseEntity<MessageDto> response = adapter.post(url,projectMemberAddRequest);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        throw new ProjectMemberNotRegisterException("projectMember not register");
    }
}