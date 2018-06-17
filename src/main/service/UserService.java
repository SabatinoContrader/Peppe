package main.service;

import main.dao.UserDAO;
import main.model.User;

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

    public String userType (String username) {
        return this.userDAO.userType(username);
    }
}