package com.pCarpet.controller;

import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pCarpet.model.Car;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;
import com.pCarpet.services.CarService;
import com.pCarpet.services.StopService;
import com.pCarpet.services.UserService;
import com.pCarpet.utils.Reference;


@Controller
@RequestMapping("/Car")
public class CarController {
	
	private UserService userService;
	private CarService carService;
	private StopService stopService;
	
	@Autowired
	public CarController(UserService userService, CarService carService, StopService stopService) {
		this.userService = userService;
		this.carService = carService;
		this.stopService = stopService;
	}
	
	@RequestMapping(value = "/carsList", method = RequestMethod.GET)
	public String getCars(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		
		List<Car> cars = carService.getAllCar(user);
		Reference<List<Car>> mycars = new Reference<List<Car>>(cars);

		model.addAttribute("cars", cars);
		return "car";
	}
	
	@RequestMapping(value = "/removeCar", method = RequestMethod.GET)
	public String removeCar(@RequestParam("id") int id_car, Model model ) {
		Car car = carService.getCar(id_car);
		Stop stop = stopService.getStop(car);
		if(stop == null) 
			carService.removeCar(id_car);
		else
			model.addAttribute("alert", "alert");
		User user = userService.getLoggedUser();
		model.addAttribute("cars", carService.getAllCar(user));
		return "car";
	}
	
	@RequestMapping(value = "/addCar", method = RequestMethod.GET)
	public String addCar(HttpServletRequest request, Model model ) {
		return "addCar";
	}
	
	@RequestMapping(value = "/addedCar", method = RequestMethod.POST)
	public String addedCar(HttpServletRequest request, Model model ) {
		String licensePlate = request.getParameter("licensePlate");
		String name = request.getParameter("name");
		String size = request.getParameter("size");
		User user = userService.getLoggedUser();
		
		Car car = new Car();
		car.setLicense_plate(licensePlate);
		car.setName(name);
		car.setSize(size);
		car.setUser(user);
		
		carService.addCar(car);
		request.setAttribute("cars", carService.getAllCar(user));
		return "car";
	}
	
	@RequestMapping(value = "/backCarsList", method = RequestMethod.POST)
	public String driverBackCarsList(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		request.setAttribute("cars", carService.getAllCar(user));
		return "car";
	}
	
	

}
