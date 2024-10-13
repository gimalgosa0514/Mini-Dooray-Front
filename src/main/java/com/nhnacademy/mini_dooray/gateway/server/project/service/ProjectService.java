package com.nhnacademy.mini_dooray.gateway.server.project.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.Project;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.gateway.server.project.domain.ProjectMemberAddRequest;
import com.nhnacademy.mini_dooray.gateway.server.project.exception.ProjectMemberNotRegisterException;
import com.nhnacademy.mini_dooray.gateway.server.project.exception.ProjectNotFoundException;
import com.nhnacademy.mini_dooray.gateway.server.project.exception.ProjectNotRegisterException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final Adapter adapter;
    private final String URL = "http://localhost:8082/api";

    public List<Project> getProjects(String memberId) {
        String url = URL+"/project/member/"+memberId;

        try{
            ResponseEntity<List<Project>> response = adapter.getList(url, new ParameterizedTypeReference<List<Project>>(){});

            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            return new ArrayList<>();
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ArrayList<>();
        }
    }

    public ProjectDetailResponse getProjectDetail(Long projectId) {
        String url = URL+"/project/"+projectId;
        try{
            ResponseEntity<ProjectDetailResponse> response = adapter.get(url,ProjectDetailResponse.class);

            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ProjectNotFoundException("project not found");
        }

        throw new ProjectNotFoundException("project not found");
    }

    public MessageDto createProject(ProjectCreateRequest request) {
        String url = URL+"/project";

        try{
            ResponseEntity<MessageDto> response = adapter.post(url,request);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new ProjectNotRegisterException("project not register");

        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ProjectNotRegisterException("project not register");

        }

    }

    public MessageDto addProjectMember(String projectId, ProjectMemberAddRequest projectMemberAddRequest) {
        String url = URL+"/project/"+projectId+"/member";

        try{
            ResponseEntity<MessageDto> response = adapter.post(url,projectMemberAddRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new ProjectMemberNotRegisterException("projectMember not register");
        }
        catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ProjectMemberNotRegisterException("projectMember not register");

        }
    }

    
}
