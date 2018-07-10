using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] // tune to your needs
    [RoutePrefix("api")]
    public class SignupController : ApiController
    {
        UserService userService;

        public SignupController()
        {
            this.userService = new UserService();
        }

        [HttpPost]
        [Route("signupUser")]
        public bool Registered(UserDTO userDTO)
        {
            if (userService.insertUser(userDTO))
                return true;
            else
                return false;
        }
    }
}
