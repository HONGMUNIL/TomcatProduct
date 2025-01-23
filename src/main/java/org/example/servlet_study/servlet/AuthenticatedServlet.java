package org.example.servlet_study.servlet;


import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.server_flow.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/authenticated")
public class AuthenticatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToken = req.getHeader("Authorization");
        System.out.println(bearerToken);
        ResponseDto responseDto = null;
        if (bearerToken == null) {
            responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token 입니다");        }


        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().print(responseDto);
    }


}
