package com.virtualpairprogrammers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.UserService;

public class HomeServlet extends HttpServlet{
	
	private UserService userService;
	
	public HomeServlet() {
		userService = new UserService();
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = userService.getLoggedUser();
        String username = user.getUsername();
        String type = user.getType();
		if (type.equals("driver"))
			getServletContext().getRequestDispatcher("/homeDriver.jsp").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/homeOwner.jsp").forward(request, response);
		
	}

}
