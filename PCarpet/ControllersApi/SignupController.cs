using PCarpet.DTO;
using PCarpet.Service;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] 
    [RoutePrefix("api")]
    public class SignupController : ApiController
    {
        UserService userService;
        PaymentService paymentService;

        public SignupController()
        {
            this.userService = new UserService();
            this.paymentService = new PaymentService();
        }

        [HttpPost]
        [Route("signupUser")]
        public bool Registered(UserDTO userDTO)
        {
            if (userService.insertUser(userDTO))
            {
                paymentService.createWallet(userDTO.username);
                return true;
            }
            else
                return false;
        }
    }
}
