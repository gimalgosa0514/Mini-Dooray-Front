package com.nhnacademy.mini_dooray.gateway.util;

import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberIdUtil {

    private MemberIdUtil(){

    }

    public static String getMemberId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
