package org.example.servlet_study.service;

import lombok.Builder;
import org.example.servlet_study.dao.BoardDao;
import org.example.servlet_study.dto.InsertBoardDto;
import org.example.servlet_study.entity.Board;

public class BoardService {


    private BoardDao boardDao;

    private static BoardService instance;
    private BoardService() {
        boardDao.getInstance();
    }

    public static BoardService getInstance() {
        if (instance == null) {
            instance = new BoardService();
        }
        return instance;
    }
    public void insertBoard(InsertBoardDto dto) {
        Board board = dto.toBoard();
        boardDao.save(board);

    }
}
