package org.example.servlet_study.servlet;

import org.example.servlet_study.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        List<User> users = new ArrayList<>();
        users.add(new User("aaa", "1111", "aaaaaa", "aaa@email.com"));
        users.add(new User("bb", "22", "b", "bb@email.com"));
        users.add(new User("ccc", "1331", "ccc", "cc@email.com"));
        users.add(new User("dddd", "444", "dd", "dd@email.com"));
        users.add(new User("eee", "5551", "ee", "ee@email.com"));
        config.getServletContext().setAttribute("users", users);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("searchValue");
        ServletContext servletContext = req.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = User.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .build();

        ServletContext servletContext = req.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");


        users.add(user);

        resp.sendRedirect("http://localhost:8080/servlet_study_war/user");
    }
}
