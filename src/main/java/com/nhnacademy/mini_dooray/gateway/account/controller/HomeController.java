package com.nhnacademy.mini_dooray.gateway.account.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "redirect:/"+SecurityContextHolder.getContext().getAuthentication().getName()+"/project";
    }
}
