package com.project.oauth.auth.controller;


import com.project.oauth.auth.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    private ResponseEntity<?> test(){
        return ResponseEntity.ok(BaseResponse.success("Hello World"));
    }


}
