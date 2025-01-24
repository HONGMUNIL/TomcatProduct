package org.example.servlet_study.dao;

import org.example.servlet_study.config.DBConnectionMgr;
import org.example.servlet_study.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private static UserDao userDao = null;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }


    public User findById(int id)  {
        User foundUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                    select
                        user_id,
                        username,
                        password,
                        name,
                        email
                    from
                        user_tb
                    where
                        user_id=?
                    """;

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                foundUser = User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build();
            }


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps, rs);
        }

        return foundUser;

    }


    public List<User> findAllBySerachValue(String SerachValue) {
        List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = DBConnectionMgr.getInstance().getConnection();
           String sql = """
        select 
            * 
        from 
            user_tb 
        where 
        username LIKE ? OR name LIKE ? OR email LIKE ?
        """;

        }catch (Exception e) {

        }
        return users;
    }


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                    select
                        user_id,
                        username,
                        password,
                        name,
                        email
                    from
                        user_tb
                    """;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(User.builder()
                        .userId(rs.getInt(1))
                        .username(rs.getString(2))
                        .password(rs.getString(3))
                        .name(rs.getString(4))
                        .email(rs.getString(5))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps); //freeConnection은 연결 해제, 예외가 터지던안터지던
        }
        return Optional.ofNullable(user);


    }
}
