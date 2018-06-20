package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Slot;
import com.virtualpairprogrammers.dto.ManagementCarPlaceDTO;
import com.virtualpairprogrammers.services.CarPlaceService;
import com.virtualpairprogrammers.services.SlotService;
import com.virtualpairprogrammers.services.StopService;



public class ManagementCarPlaceServlet extends HttpServlet {
	
	private CarPlaceService carPlaceService;
	private StopService stopService;
	private SlotService slotService;

	public ManagementCarPlaceServlet() {
		carPlaceService = new CarPlaceService();
		stopService = new StopService();
		slotService = new SlotService();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String) request.getParameter("richiesta");
		if (view.equals("home")) {
			List<Slot> slots = slotService.getAllSlot();
			request.setAttribute("slots", slots);
			getServletContext().getRequestDispatcher("/managementSlot.jsp").forward(request, response);
		} else if (view.equalsIgnoreCase("slotList")) {
			int id_slot = Integer.parseInt(request.getParameter("id"));
			List<ManagementCarPlaceDTO> managementCarPlaceDTOs = stopService.getAllStop(id_slot);
			request.setAttribute("managementCarPlaceDTO", managementCarPlaceDTOs);
			getServletContext().getRequestDispatcher("/managementCarPlace.jsp").forward(request, response);

		}
	}
}