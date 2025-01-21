package org.example.servlet_study.dao;

import org.example.servlet_study.config.DBConnectionMgr;
import org.example.servlet_study.entity.Board;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class BoardDao {

    private DBConnectionMgr dbConnectionMgr;
    private static BoardDao instance;

    public BoardDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();

    }

    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }

    public Optional<Board> save(Board board) {
        Board insertedBoard = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dbConnectionMgr.getConnection();
            String sql = """
                    insert into board_tb values(default, ?, ?)
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedBoard = Board.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build();
                int id = rs.getInt(1);
                board.setBoardId(id);
            }
        }catch (Exception e) {

            throw new RuntimeException(e);
        }finally {
            dbConnectionMgr.freeConnection(con, ps);
        }

        return Optional.ofNullable(board);
    }

}
