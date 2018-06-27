package com.pCarpet.controller;

import com.pCarpet.model.User;
import com.pCarpet.services.UserService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Signup")
public class SignupController {

	private UserService userService;

	@Autowired
	public SignupController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model ) {
	return "signup";
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String registered(HttpServletRequest request, Model model ) 
	{
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

        if (userService.insertUser(newUser))
        {
        	model.addAttribute("feedback", "success");
        	return "index";		
        }else
        {
        	model.addAttribute("feedback", "failed");
        	return "index";
        }
	}
}