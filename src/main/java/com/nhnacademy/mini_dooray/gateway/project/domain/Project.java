package com.nhnacademy.mini_dooray.gateway.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Project {
    private String id;
    private String projectName;
    private String projectStatus;
    private String projectManagerId;
}
