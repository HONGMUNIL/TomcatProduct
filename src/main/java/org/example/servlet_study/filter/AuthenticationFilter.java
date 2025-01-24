package org.example.servlet_study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.example.servlet_study.dao.UserDao;
import org.example.servlet_study.dto.ResponseDto;
import org.example.servlet_study.entity.User;
import org.example.servlet_study.security.annotation.JwtValid;
import org.example.servlet_study.security.jwt.JwtProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


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

        try {
            System.out.println(isJwtTokenValid(request));
            if(isJwtTokenValid(request)) { //Jwt토큰 유효성검사 해야하는지 아닌지.( 회원가입땐 토큰필요없으니까)
                String bearerToken = request.getHeader("Authorization");
                if(bearerToken == null) { //토큰이 없다면
                    setUnAuthenticatedResponse(response); //403응답
                    return;
                }

                Claims claims = jwtProvider.parseToken(bearerToken); //토큰 유효기간검사
                if(claims == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }

                int userId = Integer.parseInt(claims.get("userId").toString());
                User foundUser = userDao.findById(userId);
                if(foundUser == null) {
                    setUnAuthenticatedResponse(response);
                    return;
                }
            };
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isJwtTokenValid(HttpServletRequest request) throws ClassNotFoundException {
        String method = request.getMethod();
        String servletPath = request.getHttpServletMapping().getServletName(); //해당 서블렛 찾기, 없으면 404

        Class<?> servletClass = Class.forName(servletPath);
        Method foundMethod = getMappedMethod(servletClass, method);//Class.forName(servletPath);로 생성된 클래스에 메서드 생성
        return foundMethod != null;
    }

    private Method getMappedMethod(Class<?> clazz, String methodName) {
        for(Method method : clazz.getDeclaredMethods()) {
            if(method.getName().toLowerCase().endsWith(methodName.toLowerCase()) && method.isAnnotationPresent(JwtValid.class)) {
                return method;
            }
        }
        return null;
    }

    private void setUnAuthenticatedResponse(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<String> responseDto = ResponseDto.forbidden("검증 할 수 없는 Access Token 입니다.");
        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(responseDto));
    }

}

