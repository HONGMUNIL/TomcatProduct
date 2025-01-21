package org.example.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servlet_study.dto.InsertBoardDto;
import org.example.servlet_study.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/api/board")
public class BoardRestServlet extends HttpServlet {
    private BoardService boardService;

    public BoardRestServlet() {
        boardService = BoardService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder(); //문자열 합쳐줌

        try (BufferedReader bufferedReader = req.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        InsertBoardDto insertBoardDto = objectMapper.readValue(stringBuilder.toString(), InsertBoardDto.class);
        System.out.println(insertBoardDto);




    }
}
