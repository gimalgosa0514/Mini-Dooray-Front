package com.nhnacademy.mini_dooray.gateway.account.service;

import com.nhnacademy.mini_dooray.gateway.account.domain.MemberLoginResponse;
import com.nhnacademy.mini_dooray.gateway.account.domain.MemberRegistrationRequest;
import com.nhnacademy.mini_dooray.gateway.account.exception.LoginFailedException;
import com.nhnacademy.mini_dooray.gateway.account.exception.MemberRegisterFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AccountService accountService;


    @Test
    void register_success() {
        // Given
        MemberRegistrationRequest request = new MemberRegistrationRequest();
        MemberRegistrationRes expectedResponse = new MemberRegistrationResponse();
        when(restTemplate.postForEntity(anyString(), eq(request), eq(MemberRegistrationResponse.class)))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // When
        MemberRegistrationResponse actualResponse = accountService.register(request);

        // Then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void register_fail() {
        // Given
        MemberRegistrationRequest request = new MemberRegistrationRequest();
        MemberRegistrationResponse responseBody = new MemberRegistrationResponse();
        responseBody.setMessage("Registration failed");
        when(restTemplate.postForEntity(anyString(), eq(request), eq(MemberRegistrationResponse.class)))
                .thenReturn(new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST));

        // When & Then
        MemberRegisterFailedException exception = assertThrows(MemberRegisterFailedException.class, () -> {
            accountService.register(request);
        });
        assertEquals("Registration failed", exception.getMessage());
    }

    @Test
    void doLogin_success() {
        // Given
        String userId = "testUser";
        MemberLoginResponse expectedResponse = new MemberLoginResponse();
        when(restTemplate.postForEntity(anyString(), eq(userId), eq(MemberLoginResponse.class)))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // When
        MemberLoginResponse actualResponse = accountService.doLogin(userId);

        // Then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void doLogin_fail() {
        // Given
        String userId = "testUser";
        when(restTemplate.postForEntity(anyString(), eq(userId), eq(MemberLoginResponse.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        // When & Then
        LoginFailedException exception = assertThrows(LoginFailedException.class, () -> {
            accountService.doLogin(userId);
        });
        assertEquals("login failed", exception.getMessage());
    }
}
