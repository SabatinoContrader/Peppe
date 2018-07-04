using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "CAAAAAAA";

            return View();
        }

        public ActionResult Choose()
        {
            ViewBag.Title = "CAAAAAAA";

            Boolean flag = true;
            if (flag)
                return View("HomeDriver");
            else
                return View("Index");
            
        }
    }
}
