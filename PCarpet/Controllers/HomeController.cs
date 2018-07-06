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

        public ActionResult Home(string username, string password)
        {
           
            using (pcarpetEntities a = new pcarpetEntities()) {

                //a.user.Add(new user("Cacca", "Pipi", 0, "3331231231", "dasdas@"));
                //a.SaveChanges();

                //user user = a.user.Find(username);
                Boolean login = userService.login(username, password);
                if (login)
                {
                    user user = userService.getLoggedUser();
                    int type = user.type;
                    if (type == 1)
                        return View("homeDriver");
                    else if (type == 0)
                        return View("homeOwner");
                    else if (type == 2)
                        return View("homeCop");
                }
                else
                {
                    ViewBag.feedback = "loginerror";
                    return View("index");
                }
                    



            }
            return View("HomeDriver");
        }
    }
}
