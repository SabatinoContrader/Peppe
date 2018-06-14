package main.controller;

import main.MainDispatcher;

public class FindCarPlaceController implements Controller {

    @Override
    public void doControl(Request request) {
        MainDispatcher.getInstance().callView("FindCarPlace", request);

    }
}
