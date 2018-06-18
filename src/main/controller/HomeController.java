package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.UserService;

public class HomeController implements Controller {

    private UserService userService;

    public HomeController() {
        userService = new UserService();
    }

    public void doControl(Request request) {
        String type = "";
        User user = userService.getLoggedUser();
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
