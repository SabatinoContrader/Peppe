using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class LegislationController : Controller
    {
        // GET: Legislation
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Legislations()
        {
            return View();
        }
    }
}