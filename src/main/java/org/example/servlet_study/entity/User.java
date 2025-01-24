package org.example.servlet_study.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//entity는 DB랑 같게 만들어줘야한다  하지만 자바는 카멜표기법이니 주의
public class User {
    private int userId;
    private String username;
    @JsonIgnore //패스워드를 응답해줄수 없으니 설정
    private String password;
    private String name;
    private String email;


}
