package org.example.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet_study.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = new Author(1, "John Doe");
        Publisher publisher = new Publisher(10, "hong");
        BookCategory bookCategory = new BookCategory(100, "wanna home");

        Book book = Book.builder()
                .bookId(200)
                .bookName("테스트도서")
                .isbn("abcda2123")
                .bookImgUrl("http://test.com")


                .authorId(author.getAuthorId())
                .publisherId(publisher.getPublisherId())
                .categoryId(bookCategory.getCategoryId())


                .author(author)
                .publisher(publisher)
                .bookCategory(bookCategory)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);
        response.getWriter().println();

        response.setContentType("apllication/json");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
