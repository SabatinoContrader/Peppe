using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PCarpet.Controllers
{
    public class PaymentController : Controller
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

        // GET: Payment
        public ActionResult Index()
        {
            return View();
        }

        //    @RequestMapping(value = "/payment", method = RequestMethod.GET)

        //public String openPayments(HttpServletRequest request, Model model)
        //    {
        //        return "payments";
        //    }

        

        public ActionResult paymentList()
        {
            user user = userService.getLoggedUser();

            List<PaymentDTO> payments = paymentService.getAllPayment(user);
            ViewBag.payments = payments;
            return View("paymentsHystory");
        }


        public ActionResult addPayment(string timeToAdd, float totalPrice, int id_slot, int id_car)
        {

            user user = userService.getLoggedUser();

            //String timeToAdd = request.getParameter("timeToAdd");
            //Float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
            //int id_slot = Integer.parseInt(request.getParameter("id_slot"));
            //int id_car = Integer.parseInt(request.getParameter("id_car"));


            //DateTime now = DateTime.Now;
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTime start = DateTime.Now;
            DateTime finish = start.AddMinutes(Double.Parse(timeToAdd));
            //DateTime finish = nextTime.format(formatter);
            System.Diagnostics.Debug.WriteLine("START: "+ start+"FINISH: "+finish);
            //car car = carService.getCar(id_car);
            //slot slot = slotService.getSlot(id_slot);

            //stop stop = new stop(0, start, finish, false, id_car, id_slot);
            int id_stop = stopService.insertStop(new StopDTO(start, finish, id_car, id_slot));
            
            //payment payment = new payment(0, totalPrice, user, stop);
            paymentService.insertPayment(new PaymentDTO(totalPrice, user.username, id_stop));

            return View("homeDriver");
        }
    }
}