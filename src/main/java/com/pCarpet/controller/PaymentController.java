package com.pCarpet.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pCarpet.model.Car;
import com.pCarpet.model.Payment;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;
import com.pCarpet.services.CarService;
import com.pCarpet.services.PaymentService;
import com.pCarpet.services.SlotService;
import com.pCarpet.services.StopService;
import com.pCarpet.services.UserService;
import com.pCarpet.utils.Reference;


@Controller
@RequestMapping("/Payment")
public class PaymentController {
	
	private PaymentService paymentService;
	private UserService userService;
	private StopService stopService;
	private CarService carService;
	private SlotService slotService;
	
	@Autowired
	public PaymentController(PaymentService paymentService, UserService userService, StopService stopService, CarService carService, SlotService slotService) {
		this.paymentService = paymentService;
		this.userService = userService;
		this.stopService = stopService;
		this.carService = carService;
		this.slotService = slotService;
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String openPayments(HttpServletRequest request, Model model ) {
		return "payments";
	}
	
	@RequestMapping(value = "/paymentList", method = RequestMethod.GET)
	public String getPayments(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		
		List<Payment> payments = paymentService.getAllPayment(user);

		model.addAttribute("payments", payments);
		return "paymentsHystory";
	}
	
	@ResponseBody
	@PostMapping("/addPayment")
	public Payment addPayment(HttpServletRequest request, Model model ) {

		User user = userService.getLoggedUser();
		
		String timeToAdd = request.getParameter("timeToAdd");
		Float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
		int id_slot = Integer.parseInt(request.getParameter("id_slot"));
		int id_car = Integer.parseInt(request.getParameter("id_car"));
		
		System.out.println("timeToAdd: " + timeToAdd);
		System.out.println("totalPrice: " + totalPrice);
		System.out.println("id_slot: " + id_slot);
		System.out.println("id_car: " + id_car);
		
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = now.format(formatter);
        LocalDateTime nextTime = now.plusMinutes(Long.parseLong(timeToAdd));
        String finish = nextTime.format(formatter);
        
		Car car = carService.getCar(id_car);
		
		//non ci sarà il carplace!
		//mi prenderò lo slot per la creazione di carplace!
		Slot slot = slotService.getSlot(id_slot);
		
		Stop stop = new Stop(0,start,finish,false,car,slot);
		stopService.insertStop(stop);
		
		Payment payment = new Payment(0,totalPrice,user,stop);	
		paymentService.insertPayment(payment);		
		
		return payment;
	}
}