using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class HomeController : Controller
    {
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
                user user1 = a.user.FirstOrDefault(e => e.password.Equals(password) && e.username.Equals(username));
                if (user1 != null)
                {
                    int type = user1.type;
                    if (type==1)
                        //return "homeDriver";
                    ViewBag.title = "DRIVER";
                    else if (type==0)
                        //return "homeOwner";
                        ViewBag.title = "GESTORE";
                    else if (type==1)
                        //return "homeCop";
                        ViewBag.title = "COP";

                }
                else
                    ViewBag.title = "ERROR";
                    



            }
            return View("HomeDriver");
        }
    }
}
