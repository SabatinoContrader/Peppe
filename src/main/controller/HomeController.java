package main.controller;

import main.MainDispatcher;
import main.dao.LoginDAO;
import main.model.User;
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
        User user = loginService.getLoggedUser();
        //String username = request.get("username").toString();
        String username = user.getUsername();
        if (request != null) {
            type = userService.userType(username);
            if (type.equals("driver"))
                MainDispatcher.getInstance().callView("HomeDriver", request);
            else
                MainDispatcher.getInstance().callView("HomeOwner", request);
        } else {
            type = userService.userType(username);
            request = new Request();
            request.put("username", username);
            if (type.equals("driver")){
                MainDispatcher.getInstance().callView("HomeDriver", request);}
            else
                MainDispatcher.getInstance().callView("HomeOwner", request);
        }
    }
}
