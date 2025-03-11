package com.congdinh.vivuchat.services.interfaces;

import com.congdinh.vivuchat.dtos.requests.LoginRequest;
import com.congdinh.vivuchat.dtos.requests.LogoutRequest;
import com.congdinh.vivuchat.dtos.requests.RefreshTokenRequest;
import com.congdinh.vivuchat.dtos.requests.RegisterRequest;
import com.congdinh.vivuchat.dtos.responses.JwtResponse;
import com.congdinh.vivuchat.dtos.responses.MessageResponse;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    MessageResponse registerUser(RegisterRequest registerRequest);
    MessageResponse logoutUser(LogoutRequest logoutRequest);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
