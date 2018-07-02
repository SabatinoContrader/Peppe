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
import com.pCarpet.services.StopService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/FindCarPlace")
public class FindCarPlaceController {
	
	private UserService userService;
	private StopService stopService;
	
	@Autowired
	public FindCarPlaceController(UserService userService, StopService stopService)  {
		this.userService = userService;
		this.stopService = stopService;
	}
	
	@RequestMapping(value = "/showSlot", method = RequestMethod.GET) 
	public String showSlot(HttpServletRequest request, Model model ) {
		List<Car> cars = stopService.getCarWithoutStopOfUser();
		model.addAttribute("cars", cars);
		return "findCarPlace";
	
	}
	

}
