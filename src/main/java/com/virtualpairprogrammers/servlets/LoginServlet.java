package com.virtualpairprogrammers.servlets;


import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    public LoginServlet() {
        userService = new UserService();
    }

    public void doPost (HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException,IOException {

        HttpSession session = request.getSession(true);

        //if (request != null) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userService.login(username, password)){
                String type = "";
                User user = userService.getLoggedUser();
                type = user.getType();
                if (type.equals("driver"))
                    response.sendRedirect("homeDriver.jsp");
                else
                    response.sendRedirect("homeOwner.jsp");



            }
            else{
                session.setAttribute("error","Username o password sbagliati");
                response.sendRedirect("index.jsp");}
            //}else
            //MainDispatcher.getInstance().callView("Login", request);
        }

   // }
}
