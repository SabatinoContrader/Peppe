package main.controller;

import main.MainDispatcher;

public class IndexController implements Controller {


    @Override
    public void doControl(Request request) {
        MainDispatcher.getInstance().callView("Index", request);
    }
}
