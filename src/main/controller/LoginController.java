package main.controller;

import main.MainDispatcher;
import main.service.UserService;

public class LoginController implements Controller {

    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    public void doControl(Request request) {
        if (request != null) {
            String username = request.get("username").toString();
            String password = request.get("password").toString();

            if (userService.login(username, password))
                MainDispatcher.getInstance().callAction("Home", "doControl", request);
            else
                MainDispatcher.getInstance().callAction("Index", "doControl", request);
        }else
            MainDispatcher.getInstance().callView("Login", request);
    }


}
