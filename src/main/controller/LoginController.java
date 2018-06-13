package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.UserService;

import java.time.LocalDate;

import java.util.List;

public class LoginController implements Controller {

    private UserService userService;

    public LoginController() { userService = new UserService(); }

    public void doControl (Request request) {

        /*
        List<User> users = userService.getAllUserModels(false);
        for (User user : users)
        {
            System.out.println(user.toString());
        }
        */

        //User user = userService.getUserModel("username1", false);
        //System.out.println(user.toString());

        System.out.println("Debug_1");
        if (request != null) {
            String username    = request.get("username").toString();
            String password    = request.get("password").toString();
            String type        = "driver";                            //set type as driver
            String name        = request.get("name").toString();
            String surname     = request.get("surname").toString();
            LocalDate birthdate   = (LocalDate)request.get("birthdate");
            String birthplace  = request.get("birthplace").toString();
            String address     = request.get("address").toString();
            Boolean handicapped = (boolean)request.get("handicapped");

            User newUser = new User(username,password,type,name,surname,birthdate,birthplace,address,handicapped);

            System.out.println("Debug_2");
            if (userService.insertUser(newUser)) {
                System.out.println("Debug_3");
                
                MainDispatcher.getInstance().callView("Home", request);
            }
            else {
                System.out.println("Debug_4");
                MainDispatcher.getInstance().callAction("Login", "doControl", null);
            }
        }
        else {System.out.println("Debug_5"); MainDispatcher.getInstance().callView("Login", request);}

    }
}
