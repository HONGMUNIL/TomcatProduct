package org.example.servlet_study.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.security.annotation.JwtValid;
import org.example.servlet_study.security.jwt.JwtProvider;
import org.example.servlet_study.server_flow.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/authenticated")
public class AuthenticatedServlet extends HttpServlet {
    private JwtProvider jwtProvider;

    public AuthenticatedServlet() {
        jwtProvider = JwtProvider.getInstance();
    }

    @JwtValid
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization");

        ObjectMapper objectMapper = new ObjectMapper();
        Claims claims = jwtProvider.parseToken(bearerToken);
        ResponseDto<?> responseDto = ResponseDto.success(claims.get("userId"));

        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }
}
