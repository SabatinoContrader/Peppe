package com.pCarpet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pCarpet.dto.MarkerDTO;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;
import com.pCarpet.model.User;
import com.pCarpet.services.CarPlaceService;
import com.pCarpet.services.SlotService;
import com.pCarpet.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RequestMapping("/Marker")

@RestController
public class MarkerController {

	SlotService slotService;
	CarPlaceService carplaceService;

	@Autowired
	public MarkerController(SlotService slotService, CarPlaceService carplaceService) {
		this.slotService = slotService;
		this.carplaceService = carplaceService;
	}

	//@RequestMapping(value = "/updateParkings", method = RequestMethod.POST)
	//@ResponseBody
	@PostMapping("/updateParkings")
	public List<MarkerDTO> register(HttpServletRequest request, HttpServletResponse response) {
		// posto richiesto
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lng = Double.parseDouble(request.getParameter("lng"));

		// calcolo slot vicini e li aggiungo a Map chiamata al service
		List<Slot> slots = slotService.getNearSlot(lat, lng);
		System.out.println("slots: " + slots.toString());
		//List<Carplace> carplaces = new ArrayList<>();	
		
		List<MarkerDTO> markerDTOlist = new ArrayList<>();
		List<Carplace> carplaces = new ArrayList<>();	
		for (Slot slot : slots) 
		{
			carplaces  = carplaceService.getAllCarPlace(slot);
			markerDTOlist.add(new MarkerDTO(slot,carplaces));
		}
		
		return markerDTOlist;
			
		// trattandosi di una chiamata AJAX metto i valori di ritorno dentro response e
		// NON richiamo la view
	}

}