package main.controller;

import main.MainDispatcher;
import main.model.Car;
import main.model.User;
import main.service.CarService;
import main.service.LoginService;
import sun.applet.Main;

import java.util.List;

public class CarController implements Controller {

    private CarService carService;
    private LoginService loginService;

    public CarController() {
        carService = new CarService();
        loginService = new LoginService();
    }

    public void doControl(Request request) {
        if (request != null) {

            User user = loginService.getLoggedUser();
            String username = user.getUsername();

            String carViewName = request.get("carViewName").toString();
            if (carViewName.equals("ManagementCar")) {
                int choice = Integer.parseInt(request.get("choice").toString());
                if (choice == 1) {
                    MainDispatcher.getInstance().callView("AddCar", null);
                } else if (choice == 2) {
                    List<Car> cars = carService.getAllCarModel(username,false);
                    Request remove_request = new Request();
                    remove_request.put("cars", cars);
                    MainDispatcher.getInstance().callView("RemoveCar", remove_request);
                } else if (choice == 3) {
                    MainDispatcher.getInstance().callAction("Home", "doControl", null);
                }

            } else if (carViewName.equals("AddCar")) {
                String licensePlate = request.get("licensePlate").toString();
                String name = request.get("name").toString();
                String size = request.get("size").toString();

                Car car = new Car(licensePlate, name, size, username);
                if (carService.addcar(car))
                    MainDispatcher.getInstance().callView("ManagementCar", null);
                else
                    MainDispatcher.getInstance().callView("AddCar", null);

            } else if (carViewName.equals("RemoveCar")) {
                int id_car = Integer.parseInt(request.get("id_car").toString());
                carService.removecar(id_car);
                MainDispatcher.getInstance().callView("ManagementCar", request);
            }
        } else
            MainDispatcher.getInstance().callView("ManagementCar", null);
    }


}
