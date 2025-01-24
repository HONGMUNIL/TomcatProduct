package org.example.servlet_study.servlet;

import org.example.servlet_study.service.PracticeUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/practice-signin")
public class PracticeSigninRestServlet extends HttpServlet {
    private PracticeUserService practiceUserService;

    public PracticeSigninRestServlet(){
        practiceUserService = PracticeUserService.getInstance();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestJsonData = new StringBuilder();
        try (BufferedReader reader = request.getReader() ) {
            String line;
            while((line = reader.readLine()) != null){
                requestJsonData.append(line);
            }
        }
    }
}
