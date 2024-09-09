package com.project.oauth.auth.controller;


import com.project.oauth.auth.dto.BaseResponse;
import com.project.oauth.auth.dto.login.LoginRequestDto;
import com.project.oauth.auth.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        log.info("Load user by username auth {}", loginRequestDto.getUsername());
        return ResponseEntity.ok(BaseResponse.success(authenticationService.login(loginRequestDto), "Login Success"));
    }
}
