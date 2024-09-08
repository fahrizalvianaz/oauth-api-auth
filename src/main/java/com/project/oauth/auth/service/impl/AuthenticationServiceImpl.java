package com.project.oauth.auth.service.impl;

import com.project.oauth.auth.dto.login.LoginRequestDto;
import com.project.oauth.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.getName();
    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
