package main.controller;

import main.MainDispatcher;

public class BookController implements Controller{

    public void doControl(Request request) {
        MainDispatcher.getInstance().callView("Book", null);
    }
}
