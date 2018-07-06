using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class UsefulNumbersController : Controller
    {
        // GET: UsefulNumbers
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult UsefulNumbers()
        {
            return View();
        }
    }
}