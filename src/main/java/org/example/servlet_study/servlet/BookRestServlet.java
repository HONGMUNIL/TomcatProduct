package org.example.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet_study.entity.Book;
import org.example.servlet_study.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class BookRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                .bookName("안녕")
                .bookId(1)
                .author()
                .build();

        String jsonUser = objectMapper.writeValueAsString(book);
        System.out.println(jsonUser);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("apllication/json");
        response.getWriter().write(jsonUser);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
