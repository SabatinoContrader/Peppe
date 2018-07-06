using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class ExtensionStopsController : Controller
    {
        private UserService userService;
        private StopService stopService;

        public ExtensionStopsController()
        {
            this.userService = new UserService();
            this.stopService = new StopService();
        }

        // GET: ExtensionStops
        public ActionResult Index()
        {
            return View();
        }


        public ActionResult extensionStops()
        {
            user user = userService.getLoggedUser();
            List<ManagementExtensionStopDTO> managementExtensionStopDTO;
            managementExtensionStopDTO = stopService.getAllExtensionStop(user);
            ViewBag.stops = managementExtensionStopDTO;
            return View("extensionStops");
        }


        public ActionResult extendStop(String minute, String id, String address, String name, DateTime start, DateTime finish, String price)
        {
          
            user user = userService.getLoggedUser();

            int minutes = Int32.Parse(minute);
            int id_stop = Int32.Parse(id);
            float _price = float.Parse(price);
            DateTime updateddate = finish.AddMinutes(minutes);

            ManagementExtensionStopDTO item = new ManagementExtensionStopDTO(id_stop, address, start, updateddate, name, _price);
            stopService.extensionStop(item);
            List<ManagementExtensionStopDTO> managementExtensionStopDTO = stopService.getAllExtensionStop(user);
            ViewBag.stops = managementExtensionStopDTO;
            return View("extensionStops");
        }
    }
}