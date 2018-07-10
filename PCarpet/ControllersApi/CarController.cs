using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PCarpet.ControllersApi
{
    public class CarController : ApiController
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

        [HttpGet]
        [Route("api/myCarsList")]
        public List<CarDTO> carsList()
        {
            user user = userService.getLoggedUser();
            List<CarDTO> cars = carService.getAllCarDTO(user);
            return cars;
        }


        [HttpGet]
        [Route("api/removeCar")]
        public bool removeCar(int id)
        {
            stop stop = stopService.getStop(id);
            if (stop == null)
            {
                carService.removeCar(id);
                return true;
            }
            else
                return false; //auto non eliminata perchè in sosta
            //serviva per aggiornare la lista dopo la rimozione
            //user user = userService.getLoggedUser();
            //ViewBag.cars = carService.getAllCarDTO(user);
        }


        //angular
        //public ActionResult addCar()
        //{
        //    return View("addCar");
        //}


        [HttpGet]
        [Route("api/addCar")]
        public void addCar(String licensePlate, String name)
        {
            user user = userService.getLoggedUser();

            carService.addCar(new CarDTO(licensePlate, name, user.username));

            //serviva per aggiornare la lista dopo la add
            //ViewBag.cars = carService.getAllCarDTO(user);
       
        }

        //angular (back button)
        //public ActionResult backCarsList()
        //{
        //    user user = userService.getLoggedUser();
        //    ViewBag.cars = carService.getAllCarDTO(user);
        //    return View("car");
        //}
    }
}

