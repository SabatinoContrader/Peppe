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
        //private StopService stopService;
        //private CarService carService;
        //private SlotService slotService;

        public PaymentController()
        {
            this.userService = new UserService();
            this.paymentService = new PaymentService();
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

        //    @ResponseBody
        //    @PostMapping("/addPayment")

        //public Payment addPayment(HttpServletRequest request, Model model)
        //    {

        //        User user = userService.getLoggedUser();

        //        String timeToAdd = request.getParameter("timeToAdd");
        //        Float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        //        int id_slot = Integer.parseInt(request.getParameter("id_slot"));
        //        int id_car = Integer.parseInt(request.getParameter("id_car"));

        //        System.out.println("timeToAdd: " + timeToAdd);
        //        System.out.println("totalPrice: " + totalPrice);
        //        System.out.println("id_slot: " + id_slot);
        //        System.out.println("id_car: " + id_car);

        //        LocalDateTime now = LocalDateTime.now();
        //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //        String start = now.format(formatter);
        //        LocalDateTime nextTime = now.plusMinutes(Long.parseLong(timeToAdd));
        //        String finish = nextTime.format(formatter);

        //        Car car = carService.getCar(id_car);

        //        Slot slot = slotService.getSlot(id_slot);

        //        Stop stop = new Stop(0, start, finish, false, car, slot);
        //        stopService.insertStop(stop);

        //        Payment payment = new Payment(0, totalPrice, user, stop);
        //        paymentService.insertPayment(payment);

        //        return payment;
        //    }
    }
}