package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Report;
import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.ReportService;
import com.virtualpairprogrammers.services.UserService;

//import com.virtualpairprogrammers.domain.Book;
//import com.virtualpairprogrammers.services.BookService;

public class ReportServlet extends HttpServlet
{
	//CONTROLLARE METODI USATI
	
    private ReportService reportService;
    private UserService userService;

    public ReportServlet() {
        reportService = new ReportService();
        userService = new UserService();
    }
    
	public void service (HttpServletRequest request, 
					     HttpServletResponse response) 
			throws ServletException,IOException
	{		
		//HttpSession report_session = request.getSession(false);
		
	       String username = "";
	        User user = userService.getLoggedUser();
	        username = user.getUsername();
	        if(user.getType().equalsIgnoreCase("driver")) {
	            if (request != null) {
	                String reportViewName = request.getParameter("reportViewName").toString();
	                if (reportViewName.equals("Report")) {
	                    int choice = Integer.parseInt(request.getParameter("choice").toString());

	                    if (choice == 1) {
	                        //MainDispatcher.getInstance().callView("ReportSend", null);
	                        //response.sendRedirect("ReportSend.jsp");
	                        getServletContext().getRequestDispatcher("/ReportSend.jsp").forward(request, response);
	                    } else if (choice == 2) {
	                        List<Report> reports = reportService.getAllReportModels(username, false);
	                        //Request hystory_request = new Request();
	                        
	                        //report_session.setAttribute("reports", reports);
	                        request.setAttribute("reports", reports);
	                        
	                        //MainDispatcher.getInstance().callView("ReportHystory", hystory_request);
	                        //response.sendRedirect("ReportHystory.jsp");
	                        getServletContext().getRequestDispatcher("/ReportHystory.jsp").forward(request, response);
	                    }
	                } else if (reportViewName.equals("ReportSend")) {
	                    int type = Integer.parseInt(request.getParameter("type").toString());
	                    String description = request.getParameter("description").toString();
	                    String time = request.getParameter("time").toString();
	                    Report report = new Report(type, description, time, username);
	                    reportService.insertReport(report);
	                    //request = new Request();
	                    
	                    //report_session.setAttribute("username", username);
	                    request.setAttribute("username", username);
	                    
	                    //MainDispatcher.getInstance().callView("Report", request);
	                    //response.sendRedirect("Report.jsp");
	                    getServletContext().getRequestDispatcher("/Report.jsp").forward(request, response);
	                }
	            } else {
	                //request = new Request();
	            	
	                //report_session.setAttribute("username", username);
	                request.setAttribute("username", username);
	                
	                //MainDispatcher.getInstance().callView("Report", request);
	                //response.sendRedirect("Report.jsp");
	                getServletContext().getRequestDispatcher("/Report.jsp").forward(request, response);
	            }

	        } else if(user.getType().equalsIgnoreCase("gestore")) {
	            List<Report> reports = reportService.getAllReportOwner();
	            //Request owner_request = new Request();
	            
	            //report_session.setAttribute("reports", reports);
	            request.setAttribute("reports", reports);
	            
	            //MainDispatcher.getInstance().callView("ReportOwner", owner_request);
	            //response.sendRedirect("ReportOwner.jsp");
	            getServletContext().getRequestDispatcher("/ReportOwner.jsp").forward(request, response);
	        }
	   
	}
}