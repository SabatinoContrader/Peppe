using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class SignupController : Controller
    {
        UserService userService;

        public SignupController()
        {
            this.userService = new UserService();
        }

        // GET: Signup
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Signup()
        {
            return View();
        }

        public ActionResult Registered(String username, String password, String name, String surname, String address, Nullable<int> cap, String phone, String email, String handicapped)
        {
            int type = 1;

            UserDTO userDTO = new UserDTO(username, password, type, name, surname, address, cap, phone, email, handicapped);

            if (userService.insertUser(userDTO))
                ViewBag.feedback = "successfull";
            else
                ViewBag.feedback = "signuperror";
            return View("index");
        }
    }
}