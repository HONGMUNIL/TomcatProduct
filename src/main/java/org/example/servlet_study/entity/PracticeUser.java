package org.example.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PracticeUser {
    private int userId;
    private String username;
    private String password;
    private String email;
}
