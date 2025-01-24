package org.example.servlet_study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.example.servlet_study.dao.UserDao;
import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.entity.User;
import org.example.servlet_study.security.jwt.JwtProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebFilter("*")
public class AuthenticationFilter implements Filter {
    private JwtProvider jwtProvider;
    private UserDao userDao;

    public AuthenticationFilter() {
        jwtProvider = JwtProvider.getInstance();
        userDao = UserDao.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();
        String method = request.getMethod();

        ResponseDto<?> responseDto = null;

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null) {
            setUnAuthenticatedResponse(response);
            return;
        }

        Claims claims = jwtProvider.parseToken(bearerToken);
        if (bearerToken == null) {
            setUnAuthenticatedResponse(response);
            return;
        }
        int userId = Integer.parseInt(claims.get("userId").toString()); //userId가 데이터베이스에 있는지 검증
        User foundUser = userDao.findById(userId);
        if(foundUser == null) {

            setUnAuthenticatedResponse(response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isJwtTokenValid(HttpServletRequest request) {

    }

    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<String> responseDto = responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token 입니다");
        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().print(objectMapper.writeValueAsString(responseDto)); //getWriter()는 IOException 필요



    }
}








