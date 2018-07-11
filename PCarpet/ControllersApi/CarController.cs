using PCarpet.DTO;
using PCarpet.Service;
using System.Collections.Generic;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] 
    [RoutePrefix("api")]
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
        [Route("myCarsList")]
        public List<CarDTO> carsList(string username)
        {
            List<CarDTO> cars = carService.getAllCarDTO(username);
            return cars;
        }


        [HttpDelete]
        [Route("removeCar")]
        public bool removeCar(int id)
        {
            stop stop = stopService.getStop(id);
            if (stop == null)
            {
                carService.removeCar(id);
                return true;
            }
            else
                return false;
        }


        [HttpPost]
        [Route("addCar")]
        public void addCar(CarDTO carDTO)
        {

            carService.addCar(carDTO);
        }
    }
}

