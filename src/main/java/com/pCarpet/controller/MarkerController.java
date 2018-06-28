package com.pCarpet.controller;

import com.pCarpet.dto.MarkerDTO;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;
import com.pCarpet.services.CarPlaceService;
import com.pCarpet.services.SlotService;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MarkerController {

	SlotService slotService;
	CarPlaceService carplaceService;

	@Autowired
	public MarkerController(SlotService slotService, CarPlaceService carplaceService) {
		this.slotService = slotService;
		this.carplaceService = carplaceService;
	}


	@PostMapping("/updateParkings")
	public List<MarkerDTO> register(HttpServletRequest request, HttpServletResponse response) {
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lng = Double.parseDouble(request.getParameter("lng"));

		// calcolo slot vicini e li aggiungo a Map chiamata al service
		List<Slot> slots = slotService.getNearSlot(lat, lng);
		System.out.println("slots: " + slots.toString());
		
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