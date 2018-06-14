package main.controller;

import main.MainDispatcher;
import main.service.LoginService;

public class IndexController implements Controller {

    private LoginService loginService;

    public IndexController() {
        loginService = new LoginService();
    }

    @Override
    public void doControl(Request request) {
        loginService.destroyUser();
        MainDispatcher.getInstance().callView("Index", request);
    }
}
