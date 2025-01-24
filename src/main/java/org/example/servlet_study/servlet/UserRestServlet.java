package org.example.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.entity.User;
import org.example.servlet_study.security.annotation.JwtValid;
import org.example.servlet_study.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {
    private UserService userservice;
    public UserRestServlet() {
        userservice = UserService.getInstance();
    }

    @JwtValid//인증필요하면 다는 어노테이션
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        ResponseDto<?> responseDto = userservice.getUser(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(responseDto);
        System.out.println(jsonUser);

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
        response.setContentType("application/json");
        response.getWriter().write(jsonUser);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
