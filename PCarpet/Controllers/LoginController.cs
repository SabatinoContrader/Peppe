using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class LoginController : Controller
    {
        UserService userService;
        private Boolean isLogged = false;

        public LoginController()
        {
            userService = new UserService();
        }

        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Login(string username, string password)
        {

            using (pcarpetEntities a = new pcarpetEntities())
            {
                UserDTO userDTO = userService.login(username, password);
                if (userDTO != null)
                {
                    isLogged = true;
                    int type = userDTO.type;
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

        public ActionResult Logout()
        {
            userService.destroyUser();
            isLogged = false;
            return View("index");
        }
    }
}