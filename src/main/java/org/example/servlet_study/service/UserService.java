package org.example.servlet_study.service;

import org.example.servlet_study.dao.UserDao;
import org.example.servlet_study.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;
    private static UserService userService = null;

    private UserService() {
        userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    public List<User> getAllUsers(String searchValue) {
        if( searchValue == null || searchValue.isBlank()) {
            return userDao.findAll();
        }
        return userDao.findAllBySerachValue(searchValue);
    }

        public User addUser(User user){
            Optional<User> userOptional = userDao.save(user);
            return userOptional.get();
        }

}
