package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Car;
import com.virtualpairprogrammers.domain.Report;
import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.ReportService;
import com.virtualpairprogrammers.services.UserService;

//import com.virtualpairprogrammers.domain.Book;
//import com.virtualpairprogrammers.services.BookService;

public class ReportServlet extends HttpServlet {
	// CONTROLLARE METODI USATI

	private ReportService reportService;
	private UserService userService;

	public ReportServlet() {
		reportService = new ReportService();
		userService = new UserService();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		String view = request.getParameter("richiesta");

		User user = userService.getLoggedUser();
		username = user.getUsername();

		if (user.getType().equalsIgnoreCase("driver")) {
			switch (view) {
			case "home":
				getServletContext().getRequestDispatcher("/reportDriver.jsp").forward(request, response);
				break;

			case "hystoryDriver":
				List<Report> reports = reportService.getAllReportModels(username, false);
				request.setAttribute("reports", reports);
				getServletContext().getRequestDispatcher("/reportHystory.jsp").forward(request, response);
				break;

			case "addReport":
				int type = Integer.parseInt(request.getParameter("type").toString());
				String description = request.getParameter("description").toString();

				String time = (String) request.getAttribute("time");
				Report report = new Report(type, description, time, username);
				reportService.insertReport(report);

				// request.setAttribute("username", username);
				getServletContext().getRequestDispatcher("/homeDriver.jsp").forward(request, response);
				break;

			default:
				getServletContext().getRequestDispatcher("/homeDriver.jsp").forward(request, response);
				break;
			}
		} else if (user.getType().equalsIgnoreCase("gestore")) {
			switch (view) {
			case "home":
				List<Report> reports = reportService.getAllReportOwner();
				request.setAttribute("reports", reports);

				getServletContext().getRequestDispatcher("/reportOwner.jsp").forward(request, response);
				break;
			default:
				getServletContext().getRequestDispatcher("/homeOwner.jsp").forward(request, response);
				break;
			}

		}

	}
           
	
}