package com.nhnacademy.mini_dooray.gateway.milestone.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.milestone.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.gateway.milestone.exception.MilestoneDeleteFailedException;
import com.nhnacademy.mini_dooray.gateway.milestone.exception.MilestoneNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final Adapter adapter;
    private final String URL = "http://localhost:8082/api";

    public MilestoneDto getMilestone(String projectId,String taskId) {

        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";

        ResponseEntity<MilestoneDto> response = adapter.get(url, MilestoneDto.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new MilestoneNotFoundException("milestone not found");
    }

    public MessageDto deleteMilestone(String projectId,String taskId) {
        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";

        ResponseEntity<MessageDto> response = adapter.delete(url);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new MilestoneDeleteFailedException("Milestone can't delete!");
    }

    public MessageDto updateMilestone(String projectId,String taskId) {
        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";

        ResponseEntity<MessageDto> response = adapter.put(url, MessageDto.class);
        return null;
    }

}
