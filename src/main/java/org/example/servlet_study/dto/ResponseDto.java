package org.example.servlet_study.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(200, "Success", data);

    }

    public static <T> ResponseDto<T> fail(T data) {
        return new ResponseDto<>(400, "Fail", data);
    }


}
