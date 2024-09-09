package com.project.oauth.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.StyledEditorKit;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> {

    private Integer code;
    private String message;
    private T data;
    private Boolean status;

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(200, message, data, true);
    }

    public static <T> BaseResponse<T> failed(Integer code, String message) {
        return new BaseResponse<>(code, message, null, false);
    }


}
