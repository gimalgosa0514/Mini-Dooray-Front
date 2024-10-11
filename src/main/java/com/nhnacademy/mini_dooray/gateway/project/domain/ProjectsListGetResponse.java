package com.nhnacademy.mini_dooray.gateway.project.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectsListGetResponse {
    List<Project> projects;
}
