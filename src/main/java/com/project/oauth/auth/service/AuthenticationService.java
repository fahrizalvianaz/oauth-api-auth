package com.project.oauth.auth.service;

import com.project.oauth.auth.dto.login.LoginRequestDto;

public interface AuthenticationService {
    String login(LoginRequestDto loginRequestDto);
    String refreshToken(String refreshToken);
    void logout(String token);
}
