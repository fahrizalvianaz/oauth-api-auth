package com.project.oauth.auth.service.impl;

import com.project.oauth.auth.dto.login.LoginRequestDto;
import com.project.oauth.auth.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Object login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        log.info("Authentication : {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication.getPrincipal();
    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public void logout(String token) {

    }
}
