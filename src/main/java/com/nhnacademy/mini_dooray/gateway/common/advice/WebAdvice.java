package com.nhnacademy.mini_dooray.gateway.common.advice;

import com.nhnacademy.mini_dooray.gateway.server.account.exception.UnauthenticatedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebAdvice {

    @ExceptionHandler(UnauthenticatedUserException.class)
    public String handleUnauthenticatedUserException(UnauthenticatedUserException e, Model model) {
        model.addAttribute("status", HttpStatus.UNAUTHORIZED);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        model.addAttribute("message", "error!! bad request");
        return "error";
    }
}
