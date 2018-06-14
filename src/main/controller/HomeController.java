package main.controller;

import main.MainDispatcher;
import main.dao.LoginDAO;
import main.service.LoginService;
import main.service.UserService;

public class HomeController implements Controller {

    private LoginService loginService;
    private UserService userService;

    public HomeController() {
        loginService = new LoginService();
        userService = new UserService();
    }

    public void doControl(Request request) {
        String type = "";
        String username = request.get("username").toString();
        if (request != null) {
            type = userService.userType(username);
            if (type.equals("driver"))
                MainDispatcher.getInstance().callView("HomeDriver", request);
            else
                MainDispatcher.getInstance().callView("HomeOwner", request);


        } else {
            type = userService.userType(username);
            if (type.equals("driver"))
                MainDispatcher.getInstance().callView("HomeDriver", null);
            else
                MainDispatcher.getInstance().callView("HomeOwner", request);
        }
    }
}
