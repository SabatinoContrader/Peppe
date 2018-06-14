package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.LoginService;
import main.service.UserService;

public class LoginController implements Controller {

    private LoginService loginService;
    private UserService userService;

    public LoginController() {
        loginService = new LoginService();
        userService = new UserService();
    }

    public void doControl(Request request) {
        if (request != null) {
            String username = request.get("username").toString();
            String password = request.get("password").toString();

            if (loginService.login(username, password))
                MainDispatcher.getInstance().callAction("Home", "doControl", request);
            else
                MainDispatcher.getInstance().callAction("Login", "doControl", null);
        }else
            MainDispatcher.getInstance().callView("Login", request);
    }


}
