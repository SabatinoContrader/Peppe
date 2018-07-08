﻿using PCarpet.Service;
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
            List<car> cars = stopService.getCarWithoutStopOfUser();
            ViewBag.cars = cars;
            return View("findCarPlace");

        }
    }
}