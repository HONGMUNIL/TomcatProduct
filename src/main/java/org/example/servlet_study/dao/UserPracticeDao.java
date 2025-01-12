package org.example.servlet_study.dao;

import org.example.servlet_study.entity.UserPractice;

public class UserPracticeDao {
    private static UserDao userPracticeDao = null;

    private UserPracticeDao() {
    }

    public static UserPracticeDao getInstance() {
        if (userPracticeDao == null) {
            userPracticeDao = new UserPracticeDao();
        }
        return userPracticeDao;
    }
}
