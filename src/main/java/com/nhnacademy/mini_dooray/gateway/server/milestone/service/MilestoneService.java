package com.nhnacademy.mini_dooray.gateway.server.milestone.service;

import com.nhnacademy.mini_dooray.gateway.common.adapter.Adapter;
import com.nhnacademy.mini_dooray.gateway.common.dto.MessageDto;
import com.nhnacademy.mini_dooray.gateway.server.milestone.domain.MilestoneAttachRequest;
import com.nhnacademy.mini_dooray.gateway.server.milestone.domain.MilestoneDto;
import com.nhnacademy.mini_dooray.gateway.server.milestone.domain.MilestoneListResponse;
import com.nhnacademy.mini_dooray.gateway.server.milestone.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final Adapter adapter;
    private final String URL = "http://localhost:8082/api";

    public MilestoneDto getMilestone(String projectId,String taskId) {

        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";

        try{
            ResponseEntity<MilestoneDto> response = adapter.get(url, MilestoneDto.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new MilestoneNotFoundException("milestone not found");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MilestoneNotFoundException("milestone not found");
        }

    }

    public MessageDto deleteMilestone(String projectId,String taskId) {
        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";

        try{
            ResponseEntity<MessageDto> response = adapter.delete(url);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new MilestoneDeleteFailedException("Milestone can't detach");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MilestoneDeleteFailedException("Milestone can't detach");
        }

    }

    public MessageDto attachMilestone(String projectId, String taskId, MilestoneAttachRequest request) {

        String url = URL + "/project/" + projectId + "/task/" + taskId + "/milestone";
        try{
            ResponseEntity<MessageDto> response = adapter.post(url, request);

            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new MilestoneAttachFailedException("can't attach");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MilestoneAttachFailedException("can't attach");
        }

    }


    public List<MilestoneListResponse> getMilestones(Long projectId) {
        String uri = "/project/"+projectId+"/milestone";

        try{
            ResponseEntity<List<MilestoneListResponse>> response = adapter.getList(URL + uri, new ParameterizedTypeReference<List<MilestoneListResponse>>() {});
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            return new ArrayList<>();
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            return new ArrayList<>();
        }
    }

    //TODO 예외 메시지 수정
    public MessageDto reviseMilestone(String projectId, String milestoneId, MilestoneDto milestoneDto) {
        String url = URL + "/project/"+projectId+"/milestone/"+milestoneId;

        try{
            ResponseEntity<MessageDto> response = adapter.put(url, milestoneDto);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

            throw new MilestoneReviseException("찾으면 10000원 메롱~");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MilestoneReviseException("can't revise");
        }

    }

    public MessageDto deleteMilestoneFromProject(String projectId,String milestoneId) {
        String url = URL + "/project/"+projectId+"/milestone/"+milestoneId;

        try{
            ResponseEntity<MessageDto> response = adapter.delete(url);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw new MilestoneDeleteFailedException("Milestone can't delete!");
        }
        catch (HttpClientErrorException | HttpServerErrorException e){
            throw new MilestoneDeleteFailedException("can't delete");
        }

    }

}
