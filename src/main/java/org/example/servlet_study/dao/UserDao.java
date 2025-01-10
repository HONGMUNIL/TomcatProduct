package org.example.servlet_study.dao;

import org.example.servlet_study.config.DBConnectionMgr;
import org.example.servlet_study.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class UserDao {

    private static UserDao userDao = null;

    private UserDao() {}

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public Optional<User> save(User user) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
        con = DBConnectionMgr.getInstance().getConnection();
        String sql = """
        insert into user_tb 
        values(default, ?, ?, ?, ?)
        """;
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getEmail());
        ps.executeUpdate(); //insert, update, delete 할떄 executeUpdate 쓸것이다
        ResultSet keyRs = ps.getGeneratedKeys();    //이거쓰려면 Statement.RETURN_GENERATED_KEYS 이걸 매개변수로 줘야한다
        keyRs.next();
        int userid = keyRs.getInt(1);
        user.setUserId(userid);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps); //freeConnection은 연결 해제, 예외가 터지던안터지던
        }
        return Optional.ofNullable(user);


    }
}
