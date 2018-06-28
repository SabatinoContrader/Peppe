package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.model.Car;
import com.pCarpet.model.User;
import com.pCarpet.services.CarService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/FindCarPlace")
public class FindCarPlaceController {
	
	private UserService userService;
	private CarService carService;
	
	@Autowired
	public FindCarPlaceController(UserService userService, CarService carService)  {
		this.userService = userService;
		this.carService = carService;
	}
	
	@RequestMapping(value = "/showSlot", method = RequestMethod.GET) 
	public String showSlot(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		List<Car> cars = carService.getAllCar(user);
		model.addAttribute("cars", cars);
		return "findCarPlace";
	
	}
	

}
