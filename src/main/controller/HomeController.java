package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.LoginService;

public class HomeController implements Controller {

    private LoginService loginService;

    public HomeController() {
        loginService = new LoginService();
    }

    public void doControl(Request request) {
        String type = "";
        User user = loginService.getLoggedUser();
        String username = user.getUsername();
        if (request != null) {
            type = user.getType();
            if (type.equals("driver"))
                MainDispatcher.getInstance().callView("HomeDriver", request);
            else
                MainDispatcher.getInstance().callView("HomeOwner", request);
        } else {
            type = user.getType();
            request = new Request();
            request.put("username", username);
            if (type.equals("driver")) {
                MainDispatcher.getInstance().callView("HomeDriver", request);
            } else
                MainDispatcher.getInstance().callView("HomeOwner", request);
        }
    }
}
