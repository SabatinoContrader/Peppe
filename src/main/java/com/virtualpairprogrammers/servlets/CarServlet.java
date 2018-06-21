package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Car;
import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.CarService;
import com.virtualpairprogrammers.services.UserService;
import com.virtualpairprogrammers.genericclass.Reference;

public class CarServlet extends HttpServlet {

	private UserService userService;
	private CarService carService;

	public CarServlet() {
		userService = new UserService();
		carService = new CarService();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = userService.getLoggedUser();
		String username = user.getUsername();

		List<Car> cars = carService.getAllCarModel(username, true);
		Reference<List<Car>> mycars = new Reference<List<Car>>(cars);

		request.setAttribute("cars", cars);

		String view = request.getParameter("richiesta");

		switch (view) {
		case "home":
			getServletContext().getRequestDispatcher("/car.jsp").forward(request, response);
			break;

		case "addCar":
			getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
			break;

		case "addedCar":
			String licensePlate = request.getParameter("licensePlate");
			String name = request.getParameter("name");
			String size = request.getParameter("size");

			Car car = new Car(licensePlate, name, size, username);
			carService.addcar(car, mycars);
			request.setAttribute("cars", carService.getAllCarModel(username, true));
			getServletContext().getRequestDispatcher("/car.jsp").forward(request, response);
			break;

		case "removeCar":
			int id_car = Integer.parseInt(request.getParameter("id"));
			carService.removecar(id_car, mycars);
			getServletContext().getRequestDispatcher("/car.jsp").forward(request, response);
			break;
		default:
			getServletContext().getRequestDispatcher("/car.jsp").forward(request, response);
			break;
		}

	}

}
