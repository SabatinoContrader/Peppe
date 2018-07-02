package com.pCarpet.controller;

import com.pCarpet.model.Report;
import com.pCarpet.model.User;
import com.pCarpet.services.ReportService;
import com.pCarpet.services.UserService;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Report")
public class ReportController {

	private ReportService reportService;
	private UserService userService;

	@Autowired
	public ReportController(ReportService reportService , UserService userService)  {
		this.reportService = reportService;
		this.userService = userService;
	}
	
	
	@RequestMapping(value = "/driver/reports", method = RequestMethod.GET) 
	public String driverReports(HttpServletRequest request, Model model ) {
		return "reportDriver";
	}
	
	@RequestMapping(value = "/driver/hystory", method = RequestMethod.GET) 
	public String driverHystory(HttpServletRequest request, Model model ) {
		
		User user = userService.getLoggedUser();
		List<Report> reports = reportService.getAllReportModels(user);
		model.addAttribute("reports", reports);		
		return "reportHystory";
	}
	
	
	@RequestMapping(value = "/driver/addReport", method = RequestMethod.POST) 
	public String driverAddReport(HttpServletRequest request, Model model ) {
		
		User user = userService.getLoggedUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		int type = Integer.parseInt(request.getParameter("type").toString());
		String description = request.getParameter("description").toString();		
		
		Report report = new Report(0,type, description, time.toString(), "Non gestita" ,user);
		reportService.insertReport(report);

		return "homeDriver";
	}
	
	@RequestMapping(value = "/driver/ownerReport", method = RequestMethod.GET) 
	public String driverOwnerReport(HttpServletRequest request, Model model ) {
		String usernameOwner = "gestore"; //da sistemare
		User user = new User();
		user.setUsername(usernameOwner);
		List<Report> reportOwner = reportService.getAllReportModels(user);
		model.addAttribute("reports", reportOwner);
		return "reportHystory";
	}	
	
	@RequestMapping(value = "/owner/reportUser", method = RequestMethod.GET) 
	public String ownerReportuser(HttpServletRequest request, Model model ) {
		List<Report> reports = reportService.getAllReportOwner();
		model.addAttribute("reports", reports);
		return "reportOwner";
	}
	
	@RequestMapping(value = "/owner/addReport", method = RequestMethod.GET) 
	public String ownerAddReport(HttpServletRequest request, Model model ) {
		return "addReportOwner";
	}
	
	@RequestMapping(value = "/owner/addedReport", method = RequestMethod.POST) 
	public String ownerAddedReport(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		String description = request.getParameter("description").toString();
		int type = 0; //owner type
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		Report report = new Report(0,type, description, time.toString(), "In lavorazione" ,user);
		reportService.insertReport(report);
		return "homeOwner";
	}
	
	@RequestMapping(value = "/owner/hystory", method = RequestMethod.GET) 
	public String ownerHystory(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		List<Report> reportsOwner = reportService.getAllReportModels(user);
		model.addAttribute("reports", reportsOwner);
		return "reportHystory";
	}
	
	@RequestMapping(value = "/back", method = RequestMethod.GET) 
	public String back(HttpServletRequest request, Model model ) {
		User user = userService.getLoggedUser();
		if(user.getType().equalsIgnoreCase("driver")) {return "homeDriver";}
		else { return "addReportOwner";}
		
	}		
	
}