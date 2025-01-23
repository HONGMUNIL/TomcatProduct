package org.example.servlet_study.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeResponseDto<T> {
    private int status;
    private String message;
    private T data;

    public static <T> PracticeResponseDto<T> success(T data) {
        return new PracticeResponseDto<>(200, "Success", data);

    }

    public static <T> PracticeResponseDto<T> fail(T data) {
        return new PracticeResponseDto<>(400, "Fail", data);
    }
}
