package com.virtualpairprogrammers.servlets;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.Slot;
import com.virtualpairprogrammers.services.SlotService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class MarkersServlet extends HttpServlet {
	
	SlotService slotService;
	
	public MarkersServlet() {
		this.slotService = new SlotService();
	}

	final static Logger logger = Logger.getLogger(MarkersServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// posto richiesto
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lng = Double.parseDouble(request.getParameter("lng"));

		// calcolo slot vicini e li aggiungo a Map chiamata al service
		List<Slot> slots = slotService.getNearSlot(lat, lng);
        
		JSONArray objArray= new JSONArray();
		
		// Trasformo la lista in Json
		for(Slot slot : slots)
		{
		JSONObject obj = new JSONObject();
		obj.put("lat", slot.getLatitude());
		obj.put("lng", slot.getLongitude());		
		objArray.put(obj);
		}

		response.setContentType("application/json");
		response.getWriter().write(objArray.toString());
		
		//trattandosi di una chiamata AJAX metto i valori di ritorno dentro response e NON richiamo la view
	}

}
