package com.nhnacademy.mini_dooray.gateway.account.controller;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.account.exception.MemberRegisterFailedException;
import com.nhnacademy.mini_dooray.gateway.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final AccountService accountService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute MemberRegistrationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MemberRegisterFailedException("회원가입 오류");
        }

        accountService.register(request);
        return "redirect:/login";
    }


    @ExceptionHandler(MemberRegisterFailedException.class)
    public String handleMemberRegisterException(MemberRegisterFailedException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "register";
    }
}
