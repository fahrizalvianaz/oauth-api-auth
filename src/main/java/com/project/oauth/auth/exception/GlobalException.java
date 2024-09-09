package com.project.oauth.auth.exception;


import com.project.oauth.auth.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> handleCustomException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(BaseResponse.failed(exception.getStatusCode().value(), exception.getReason()));
    }


}
