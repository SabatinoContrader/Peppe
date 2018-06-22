package com.virtualpairprogrammers.servlets;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignupServlet extends HttpServlet {

    private UserService userService;

    public SignupServlet() {
        userService = new UserService();
    }
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String result = request.getParameter("richiesta");

		if (result.equals("register"))
			getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
		else if (result.equals("registed")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String birthdate = request.getParameter("birthdate");
			String birthplace = request.getParameter("birthplace");
			String address = request.getParameter("address");
			boolean handicapped = Boolean.valueOf(request.getParameter("handicapped"));
			String type = "driver";
			
			
			User newUser = new User(username, password, type, name, surname, birthdate, birthplace, address, handicapped);

            if (userService.insertUser(newUser)) {
            	session.removeAttribute("feedback");
            	request.setAttribute("feedback", "Registrazione effettuata con successo");
            	getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			
		}else {
			request.setAttribute("feedback", "Registrazione non effettuata");
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}
	}
}
