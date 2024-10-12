package com.nhnacademy.mini_dooray.gateway.server.tag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {
    private Long tagId;
    private String tagName;
}
