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

    public boolean signup (String username, String password, String type, String birthdate, String homeaddress ) {
        return this.loginDAO.signup(username, password, type, birthdate, homeaddress );
    }

    public boolean signup (User user) {
        return this.userService.insertUser(user);
    }

    public User getLoggedUser (){
        return this.loginDAO.getLoggedUser();
    }

    public void destroyUser(){
        this.loginDAO.destroyUser();
    }
}
