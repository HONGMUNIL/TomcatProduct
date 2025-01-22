package org.example.servlet_study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.servlet_study.entity.PracticeUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeSignupDto {
    private String username;
    private String password;
    private String email;

    public PracticeUser toPracticeUser() {
        return PracticeUser.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
