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
@RequestMapping("/Home")
public class HomeController {

	private UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/dispatchHome", method = RequestMethod.POST)
	public String dispatchHomePost(HttpServletRequest request, Model model ) {
		
		User user = userService.getLoggedUser();
        String type = user.getType();
		if (type.equals("driver"))
			return "homeDriver";
		else if (type.equals("gestore"))
			return "homeOwner";
		else if (type.equals("cop"))
			return "homeCop";
		else
			return "";
	}
	
	@RequestMapping(value = "/dispatchHome", method = RequestMethod.GET)
	public String dispatchHomeGet(HttpServletRequest request, Model model ) {
		
		User user = userService.getLoggedUser();
        String type = user.getType();
		if (type.equals("driver"))
			return "homeDriver";
		else if (type.equals("gestore"))
			return "homeOwner";
		else if (type.equals("cop"))
			return "homeCop";
		else
			return "";
	}

}