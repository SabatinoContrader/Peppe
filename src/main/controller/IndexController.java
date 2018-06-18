package main.controller;

import main.MainDispatcher;
import main.service.UserService;

public class IndexController implements Controller {

    private UserService userService;

    public IndexController() {
        userService = new UserService();
    }

    @Override
    public void doControl(Request request) {
        userService.destroyUser();
        MainDispatcher.getInstance().callView("Index", request);
    }
}
