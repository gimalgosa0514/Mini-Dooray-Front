package com.nhnacademy.mini_dooray.gateway.common.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebAdvice {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
