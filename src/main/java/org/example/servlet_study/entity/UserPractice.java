package org.example.servlet_study.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPractice {
    private int id;
    private String password;
    private String name;
    private String email;
}
