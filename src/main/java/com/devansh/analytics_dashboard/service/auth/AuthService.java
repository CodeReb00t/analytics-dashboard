package com.devansh.analytics_dashboard.service.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devansh.analytics_dashboard.dto.request.SignupRequestDTO;
import com.devansh.analytics_dashboard.dto.response.Result;
import com.devansh.analytics_dashboard.models.UserModel;
import com.devansh.analytics_dashboard.repository.auth.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public Result<String> signup(SignupRequestDTO signupRequest) {
        String userName = signupRequest.getUsername();
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();

        Optional<UserModel> existingUser = authRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return Result.error("Email is already in use");
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        authRepository.save(newUser);
        return Result.success("User registered successfully");
    }
}
