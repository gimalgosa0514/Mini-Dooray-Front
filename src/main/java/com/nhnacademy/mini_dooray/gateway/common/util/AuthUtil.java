package com.nhnacademy.mini_dooray.gateway.common.util;

import com.nhnacademy.mini_dooray.gateway.server.account.exception.UnauthenticatedUserException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AuthUtil {

    public static String getMemberId() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails && auth.isAuthenticated()) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }
        throw new UnauthenticatedUserException("unauthenticated user");
    }

    public static Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails && auth.isAuthenticated()) {
            return auth;
        }

        return null;
    }

    public static boolean isAdmin(String projectMangerId){
        return getMemberId().equals(projectMangerId);
    }
}
