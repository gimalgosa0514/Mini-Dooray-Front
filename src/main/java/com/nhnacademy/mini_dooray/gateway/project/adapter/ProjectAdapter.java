package com.nhnacademy.mini_dooray.gateway.project.adapter;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectCreateRequest;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectCreateResponse;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectDetailResponse;
import com.nhnacademy.mini_dooray.gateway.project.domain.ProjectsListGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProjectAdapter {

    private final String PROJECT_API_URL = "http://localhost:8082";
    private final RestTemplate restTemplate;

    public ProjectsListGetResponse getProjectList() {

        String uri = "/api/project";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<ProjectsListGetResponse> responseEntity = restTemplate.exchange(PROJECT_API_URL + uri, HttpMethod.GET, requestEntity, ProjectsListGetResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        throw new RuntimeException();
    }

    public ProjectDetailResponse getProject(String projectId) {
        String uri = "/api/project/" + projectId;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<ProjectDetailResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ProjectDetailResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        throw new RuntimeException();

    }




    public ProjectCreateResponse createProject(ProjectCreateRequest request) {
        String uri = "/api/project";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProjectCreateRequest> requestEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<ProjectCreateResponse> responseEntity = restTemplate.exchange(PROJECT_API_URL + uri, HttpMethod.POST, requestEntity, ProjectCreateResponse.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        throw new RuntimeException();
    }
}
