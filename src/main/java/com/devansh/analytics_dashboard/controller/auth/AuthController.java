package com.devansh.analytics_dashboard.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devansh.analytics_dashboard.dto.request.SignupRequestDTO;
import com.devansh.analytics_dashboard.dto.response.Result;
import com.devansh.analytics_dashboard.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Result<?> signup(@Valid @RequestBody SignupRequestDTO signupRequest) {
        return Result.success(authService.signup(signupRequest));
    }
}
