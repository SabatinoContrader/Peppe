package com.pCarpet.controller;

import com.pCarpet.model.User;
import com.pCarpet.services.UserService;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/Login")
public class LoginController {

	private UserService userService;
	private boolean isLogged = false;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/loginControl", method = RequestMethod.POST)
	public String loginControl(HttpServletRequest request, Model model ) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (userService.login(username, password)) {
			isLogged = true;
			String type = "";
			User user = userService.getLoggedUser();
			type = user.getType();
			if (type.equals("driver"))
				return "homeDriver";
			else if (type.equals("gestore"))
				return "homeOwner";
			else if (type.equals("cop"))
				return "homeCop";
			else
				return "index";
		}
		else 
		{
			isLogged = false;
			model.addAttribute("feedback", "wrong");
			return "index";
		}
	}
	
	@RequestMapping(value = "/logoutControl", method = RequestMethod.GET)
	public String logoutControl(HttpServletRequest request, Model model ) 
	{
		userService.destroyUser();
		isLogged = false;
		return "index";
	}
}