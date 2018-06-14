package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.UserService;

import java.time.LocalDate;

public class SignupController implements Controller {

    private UserService userService;

    public SignupController() {
        userService = new UserService();
    }

    public void doControl (Request request) {
        if(request != null){
            String username    = request.get("username").toString();
            String password    = request.get("password").toString();
            String type        = request.get("type").toString();
            String name        = request.get("name").toString();
            String surname     = request.get("surname").toString();
            LocalDate birthdate   = (LocalDate)request.get("birthdate");
            String birthplace  = request.get("birthplace").toString();
            String address     = request.get("address").toString();
            Boolean handicapped = (boolean)request.get("handicapped");

            User newUser = new User(username,password,type,name,surname,birthdate,birthplace,address,handicapped);

            if (userService.insertUser(newUser)) {
                MainDispatcher.getInstance().callView("Login", request);
            }
            else {
                MainDispatcher.getInstance().callAction("Signup", "doControl", null);
            }
        }else
            MainDispatcher.getInstance().callView("Signup", request);
    }
}