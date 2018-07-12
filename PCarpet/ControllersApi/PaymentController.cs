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
    public class PaymentController : ApiController
    {
        private PaymentService paymentService;
        private UserService userService;
        private StopService stopService;
        private CarService carService;
        private SlotService slotService;

        public PaymentController()
        {
            this.userService = new UserService();
            this.paymentService = new PaymentService();
            this.carService = new CarService();
            this.slotService = new SlotService();
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("paymentList")]
        public List<PaymentDTO> paymentList(string username)
        {
            List<PaymentDTO> payments = paymentService.getAllPayment(username);
            return payments;
        }


        [HttpGet]
        [Route("addPayment")]
        public void addPayment(string timeToAdd, float totalPrice, int id_slot, int id_car)
        {
            user user = userService.getLoggedUser();
            DateTime start = DateTime.Now;
            DateTime finish = start.AddMinutes(Double.Parse(timeToAdd));
            int id_stop = stopService.insertStop(new StopDTO(start, finish, id_car, id_slot));
            paymentService.insertPayment(new PaymentDTO(totalPrice, user.username, id_stop));
        }
    }
}

