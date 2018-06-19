package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.dto.ManagementCarPlaceDTO;
import com.virtualpairprogrammers.services.CarPlaceService;
import com.virtualpairprogrammers.services.StopService;

//import com.virtualpairprogrammers.domain.Book;
//import com.virtualpairprogrammers.services.BookService;

public class ManagementCarPlaceServlet extends HttpServlet
{
	//CONTROLLARE METODI USATI
	
    private CarPlaceService carPlaceService;
    private StopService stopService;

    public ManagementCarPlaceServlet() {
        carPlaceService = new CarPlaceService();
        stopService = new StopService();
    }
    
	public void service (HttpServletRequest request, 
					     HttpServletResponse response) 
			throws ServletException,IOException
	{		
		//HttpSession managementmarplace_session = request.getSession(false);
		
        int id_slot = Integer.parseInt( request.getParameter("id_slot") );
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = stopService.getAllStop(id_slot);
        
        //managementmarplace_session.setAttribute("managementCarPlaceDTOs", managementCarPlaceDTOs);
        request.setAttribute("managementCarPlaceDTOs", managementCarPlaceDTOs);
        
        //MainDispatcher.getInstance().callView("ManagementCarPlace", request);	  
        //response.sendRedirect("ManagementCarPlace.jsp");
        getServletContext().getRequestDispatcher("/ManagementCarPlace.jsp").forward(request, response);
	}
}