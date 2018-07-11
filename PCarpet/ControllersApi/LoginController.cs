using PCarpet.DTO;
using PCarpet.Service;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("api")]
    public class LoginController : ApiController
    {
        UserService userService;

        public LoginController()
        {
            userService = new UserService();
        }

        [HttpGet]
        [Route("login")]
        public UserDTO Login(string username, string password)
        {
            return userService.login(username, password);
        }

        [HttpGet]
        [Route("logout")]
        public void Logout()
        {
            userService.destroyUser();
        }
    
}
}
