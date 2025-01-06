package org.example.servlet_study.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {
    private String username;
    private String password;
    private String name;
    private String email;


}
