using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PCarpet.ControllersApi
{
    public class SignupController : ApiController
    {
        UserService userService;

        public SignupController()
        {
            this.userService = new UserService();
        }

        [HttpPost]
        [Route("api/signupUser")]
        public bool Registered(String username, String password, String name, String surname, String address, Nullable<int> cap, String phone, String email, String handicapped)
        {
            int type = 1;
            UserDTO userDTO = new UserDTO(username, password, type, name, surname, address, cap, phone, email, handicapped);
            if (userService.insertUser(userDTO))
                return true;
            else
                return false;
        }
    }
}
