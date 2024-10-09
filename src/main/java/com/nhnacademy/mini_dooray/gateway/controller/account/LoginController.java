<<<<<<<< HEAD:src/main/java/com/nhnacademy/mini_dooray/gateway/account/controller/LoginController.java
package com.nhnacademy.mini_dooray.gateway.account.controller;
========
package com.nhnacademy.mini_dooray.gateway.controller.account;
>>>>>>>> d9fa0b72bc4c4f468a790b84e9cd4bd0c749bfca:src/main/java/com/nhnacademy/mini_dooray/gateway/controller/account/LoginController.java

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/";
    }
}
