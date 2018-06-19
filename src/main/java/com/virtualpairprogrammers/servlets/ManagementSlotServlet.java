package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Slot;
import com.virtualpairprogrammers.services.SlotService;

//import com.virtualpairprogrammers.domain.Book;
//import com.virtualpairprogrammers.services.BookService;

public class ManagementSlotServlet extends HttpServlet
{
	//CONTROLLARE METODI USATI
	
    private SlotService slotService;

    public ManagementSlotServlet() {
        slotService = new SlotService();
    }
    
	public void service (HttpServletRequest request, 
					     HttpServletResponse response) 
			throws ServletException,IOException
	{		
		//HttpSession managementslot_session = request.getSession(false);
		
        List<Slot> slots = slotService.getAllSlot();
        //request = new Request();
        
        //managementslot_session.setAttribute("slots", slots);
        request.setAttribute("slots", slots);
        
        //MainDispatcher.getInstance().callView("ManagementSlot", request);
        //response.sendRedirect("ManagementSlot.jsp");
        getServletContext().getRequestDispatcher("/ManagementSlot.jsp").forward(request, response);
	   
	}
}