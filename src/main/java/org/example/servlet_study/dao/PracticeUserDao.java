package org.example.servlet_study.dao;

import org.example.servlet_study.config.DBConnectionMgr;
import org.example.servlet_study.entity.PracticeUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PracticeUserDao {
    private DBConnectionMgr dbConnectionMgr;
    private static PracticeUserDao instance;


    public PracticeUserDao() {
        dbConnectionMgr = DBConnectionMgr.getInstance();
    }

    public static PracticeUserDao getInstance() {
        if (instance == null) {
            instance = new PracticeUserDao();
        }
        return instance;
    }

    public PracticeUser signup(PracticeUser practiceUser) {
        PracticeUser insertedPracticeUser = null;
        Connection con = null;
        PreparedStatement ps = null;


        try {
            con = dbConnectionMgr.getConnection();
            String sql = "insert into practiceuser_tb values(default, ?, ?, ?)";
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, practiceUser.getUsername());
            ps.setString(2, practiceUser.getPassword());
            ps.setString(3, practiceUser.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedPracticeUser = PracticeUser.builder()
                        .userId(rs.getInt(1))
                        .username(practiceUser.getUsername())
                        .password(practiceUser.getPassword())
                        .email(practiceUser.getEmail())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionMgr.freeConnection(con, ps);
        }
        return insertedPracticeUser;
    }
}

