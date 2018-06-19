package com.virtualpairprogrammers.services;


import com.virtualpairprogrammers.dao.UserDAO;
import com.virtualpairprogrammers.domain.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public  UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUserModels (boolean force) {
        return this.userDAO.getAllUserModels(force);
    }

    public boolean insertUser (User user) {
        return this.userDAO.insertUser(user);
    }

    public boolean login (String username, String password ) {
        return this.userDAO.login(username, password );
    }

    public User getLoggedUser (){
        return this.userDAO.getLoggedUser();
    }

    public void destroyUser(){
        this.userDAO.destroyUser();
    }
}