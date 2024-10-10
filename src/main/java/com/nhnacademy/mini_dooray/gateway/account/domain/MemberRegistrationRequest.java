package com.nhnacademy.mini_dooray.gateway.account.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberRegistrationRequest {

    @NotNull
    @Length(min = 5, max = 20)
    private String id;

    @NotNull
    @Length(min = 8, max = 20)
    private String password;

    @NotNull
    @Email
    @Length(max = 50)
    private String  email;
}
