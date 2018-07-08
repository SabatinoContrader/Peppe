using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class ManagementParkController : Controller
    {
        private SlotService slotService;
        private UserService userService;
        private StopService stopService;

        public ManagementParkController()
        {
            this.slotService = new SlotService();
            this.userService = new UserService();
            this.stopService = new StopService();
        }

        //public ManagementParkController(SlotService slotService, UserService userService, StopService stopService)
        //{
        //    this.slotService = slotService;
        //    this.userService = userService;
        //    this.stopService = stopService;
        //}

        public ActionResult ManagementParkControl()
        {
            List<ManagementCarPlaceDTO> managementCarPlaceDTO = stopService.getAllStopDTOByCurrentUser();

            ViewBag.ManagementCarPlaceDTO = managementCarPlaceDTO;
            return View("managementPark");

        }
    }
}