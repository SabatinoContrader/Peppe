package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.dto.ManagementExtensionStopDTO;
import com.virtualpairprogrammers.services.StopService;
import com.virtualpairprogrammers.services.UserService;

public class ExtensionStopServlet extends HttpServlet
{
	//CONTROLLARE METODI USATI
	
    private UserService userService;
    private StopService stopService;

    public ExtensionStopServlet() {
        userService = new UserService();
        stopService = new StopService();
    }

    
	public void service (HttpServletRequest request, 
					     HttpServletResponse response) 
			throws ServletException,IOException
	{		
	       User user = userService.getLoggedUser();
	       List<ManagementExtensionStopDTO> managementExtensionStopDTO;
	        
	       //Request request_extension = new Request();
	       //HttpSession extensionstop_session = request.getSession(false);
	       
	        if (request != null) {
	            String extensionStopViewName = request.getParameter("extensionStopViewName").toString();

	            if(extensionStopViewName.equalsIgnoreCase("ExtensionStop")) {
	                ManagementExtensionStopDTO ExtensionStop_DTO = (ManagementExtensionStopDTO)request.getAttribute("managementExtensionStopDTO");
	                stopService.extensionStop(ExtensionStop_DTO); 

	                //MainDispatcher.getInstance().callAction("Home", "doControl", null);
                	if(user.getType().equals("driver")) 
                		{
                		//response.sendRedirect("HomeDriver.jsp");
                		getServletContext().getRequestDispatcher("/HomeDriver.jsp").forward(request, response);
                		}
                	else               {
                		//response.sendRedirect("HomeOwner.jsp");
                		getServletContext().getRequestDispatcher("/HomeOwner.jsp").forward(request, response);
                	}

	            } else if(extensionStopViewName.equalsIgnoreCase("ExtensionStops")){
	                ManagementExtensionStopDTO managementSelectedExtensionStopDTO = (ManagementExtensionStopDTO) request.getAttribute("managementExtensionStopDTO");
	  
	                //extensionstop_session.setAttribute("managementExtensionStopDTO", managementSelectedExtensionStopDTO);
	                request.setAttribute("managementExtensionStopDTO", managementSelectedExtensionStopDTO);
	                
	                //MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
	                //response.sendRedirect("ExtensionStop.jsp");
	                getServletContext().getRequestDispatcher("/ExtensionStop.jsp").forward(request, response);
	            }
	        } else {
	            managementExtensionStopDTO = stopService.getAllExtensionStop(user.getUsername());
	            if (managementExtensionStopDTO.size() == 1) {
	            	
	                //extensionstop_session.setAttribute("managementExtensionStopDTO", managementExtensionStopDTO.get(0));
	                request.setAttribute("managementExtensionStopDTO", managementExtensionStopDTO.get(0));
	                
	                //MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
	                //response.sendRedirect("ExtensionStop.jsp");
	                getServletContext().getRequestDispatcher("/ExtensionStop.jsp").forward(request, response);
	                
	            } else if (managementExtensionStopDTO.size() > 1) {
	            	
	                //extensionstop_session.setAttribute("managementExtensionStopDTO", managementExtensionStopDTO);
	                request.setAttribute("managementExtensionStopDTO", managementExtensionStopDTO);
	                             
	                //MainDispatcher.getInstance().callView("ExtensionStops", request_extension);
	                //response.sendRedirect("ExtensionStops.jsp");
	                getServletContext().getRequestDispatcher("/ExtensionStops.jsp").forward(request, response);
	            } else {
	            	
	                //extensionstop_session.setAttribute("managementExtensionStopDTO", new ManagementExtensionStopDTO());
	                request.setAttribute("managementExtensionStopDTO", new ManagementExtensionStopDTO());
	                
	                //MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
	                //response.sendRedirect("ExtensionStop.jsp");
	                getServletContext().getRequestDispatcher("/ExtensionStop.jsp").forward(request, response);
	            }
	        }
	   
	}
}