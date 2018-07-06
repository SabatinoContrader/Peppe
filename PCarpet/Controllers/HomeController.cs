using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class HomeController : Controller
    {
        UserService userService;

        public HomeController()
        {
            userService = new UserService();
        }

        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            return View();
        }

        public ActionResult dispatchHome()
        {
            user user = userService.getLoggedUser();
            int type = user.type;
            if (type == 1)
                return View("homeDriver");
            else if (type == 0)
                return View("homeOwner");
            else if (type == 2)
                return View("homeCop");
            return View();
        }
    }
}
