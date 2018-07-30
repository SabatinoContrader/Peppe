using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
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
        public AllPaymentDTO paymentList(string username)
        {
            AllPaymentDTO payments = paymentService.getAllPayment(username);
            return payments;
        }


        [HttpPost]
        [Route("addPayment")]
        public int addPayment(PaymentDTO paymentDTO)
        {
            DateTime start = DateTime.Now;
            DateTime finish = start.AddMinutes(paymentDTO.timeToAdd);

            //System.Diagnostics.Debug.WriteLine("id_car: " + paymentDTO.id_car + "id_slot: " + paymentDTO.id_slot);

            int id_stop = stopService.insertStop(new StopDTO(start, finish, paymentDTO.id_car, paymentDTO.id_slot));
            paymentDTO.id_stop = id_stop;
            paymentService.insertPayment(paymentDTO);
            return paymentDTO.id_car;
        }

        [HttpPost]
        [Route("executePayment")]
        public int executePayment()
        {
            var token = HttpContext.Current.Request.Params["token"];
            int amount = Convert.ToInt32(HttpContext.Current.Request.Params["amount"]);

            System.Console.WriteLine("token " + token);
            System.Console.WriteLine("amount " + amount);

            return paymentService.setAmount(token, amount);
        }

        [HttpGet]
        [Route("getWallet")]
        public WalletDTO getWallet(string username)
        {
            return paymentService.getWallet(username);
        }

        [HttpPut]
        [Route("modifyWallet")]
        public double modifyWallet()
        {
            var username = HttpContext.Current.Request.Params["username"];
            int money = Convert.ToInt32(HttpContext.Current.Request.Params["money"]);

            System.Console.WriteLine("username " + username);
            System.Console.WriteLine("money " + money);

            return paymentService.modifyWallet(username, money);
        }

    }
}

