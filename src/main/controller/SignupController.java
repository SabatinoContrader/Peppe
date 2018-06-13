package main.controller;

import main.MainDispatcher;

public class SignupController implements Controller {

    public SignupController() {
    }

    public void doControl (Request request) {
        MainDispatcher.getInstance().callView("Signup", request);
    }
}