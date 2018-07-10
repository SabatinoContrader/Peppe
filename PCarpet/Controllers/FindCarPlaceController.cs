using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class FindCarPlaceController : Controller
    {
        //private UserService userService;
        private StopService stopService;

        public FindCarPlaceController()
        {
            //this.userService = new UserService();
            this.stopService = new StopService();
        }

        // GET: FindCarPlace
        public ActionResult Index()
        {
            return View();
        }



        public ActionResult showSlot()
        {
            List<CarDTO> carsDTO = stopService.getCarWithoutStopOfUser();
            ViewBag.cars = carsDTO;
            return View("findCarPlace");

        }
    }
}