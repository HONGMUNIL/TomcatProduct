package org.example.servlet_study.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.example.servlet_study.dto.ResponseDto;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization");
        ResponseDto responseDto = null;
        ObjectMapper objectMapper = new ObjectMapper();


        if (bearerToken == null) {
            responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token 입니다");
            resp.setStatus(responseDto.getStatus());
            resp.setContentType("application/json");
            resp.getWriter().print(objectMapper.writeValueAsString(responseDto));
                return;
        }

        Claims claims = jwtProvider.parseToken(bearerToken);
        if(claims == null) {
            responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token 입니다");
            resp.setStatus(responseDto.getStatus());
            resp.setContentType("application/json");
            resp.getWriter().print(objectMapper.writeValueAsString(responseDto));
            return;
        }

        responseDto = ResponseDto.success(claims.get("userId"));//계정의 id 응답
        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().print(objectMapper.writeValueAsString(responseDto));
    }


}
