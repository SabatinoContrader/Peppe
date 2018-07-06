using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class CarController : Controller
    {

        private UserService userService;
        private CarService carService;
        private StopService stopService;

        public CarController()
        {
            userService = new UserService();
            carService = new CarService();
            stopService = new StopService();
        }

        public ActionResult carsList()
        {
            user user = userService.getLoggedUser();

            List<car> cars = carService.getAllCar(user);
            //Reference<List<Car>> mycars = new Reference<List<Car>>(cars);

            ViewBag.cars = cars;

            return View("car");
        }



        public ActionResult removeCar(int id)
        {
            car car = carService.getCar(id);

            stop stop = stopService.getStop(car);
            if (stop == null)
                carService.removeCar(car);
            else
                ViewBag.alert = "alert";
            user user = userService.getLoggedUser();
            ViewBag.cars = carService.getAllCar(user);
            return View("car");
        }



        public ActionResult addCar()
        {
            return View("addCar");
        }



        public ActionResult addedCar(String licensePlate, String name)
        {
            user user = userService.getLoggedUser();

            car car = new car();
            car.license_plate = licensePlate;
            car.name = name;
            car.username = user.username;

            carService.addCar(car);
            ViewBag.cars = carService.getAllCar(user);
            return View("car");
        }

        public ActionResult backCarsList()
        {
            user user = userService.getLoggedUser();
            ViewBag.cars = carService.getAllCar(user);
            return View("car");
        }
    }
}