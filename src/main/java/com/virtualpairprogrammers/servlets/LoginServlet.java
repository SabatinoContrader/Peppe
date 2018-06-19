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
	private boolean isLogged = false;

	public LoginServlet() {
		userService = new UserService();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		

		if (request != null && !isLogged) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (userService.login(username, password)) {
				isLogged = true;
				String type = "";
				User user = userService.getLoggedUser();
				type = user.getType();
				if (type.equals("driver"))
					getServletContext().getRequestDispatcher("/homeDriver.jsp").forward(request, response);
				else
					getServletContext().getRequestDispatcher("/homeOwner.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Username o password sbagliati");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		}else {
			session.invalidate();
			userService.destroyUser();
			isLogged = false;
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}
	
	
	
}
