package main.service;

import main.dao.LoginDAO;
import main.model.User;

public class LoginService {

    private LoginDAO loginDAO;
    private UserService userService;

    public LoginService() {
        this.loginDAO = new LoginDAO();
        this.userService = new UserService();
    }

    public boolean login (String username, String password ) {
        return this.loginDAO.login(username, password );
    }

    public User getLoggedUser (){
        return this.loginDAO.getLoggedUser();
    }

    public void destroyUser(){
        this.loginDAO.destroyUser();
    }
}
