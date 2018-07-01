package com.pCarpet.controller;

import com.pCarpet.model.Slot;
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

	@Autowired
	public MarkerController(SlotService slotService) {
		this.slotService = slotService;
	}


	@PostMapping("/updateParkings")
	public List<Slot> register(HttpServletRequest request, HttpServletResponse response) {
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lng = Double.parseDouble(request.getParameter("lng"));

		// calcolo slot vicini e li aggiungo a Map chiamata al service
		List<Slot> slots = slotService.getNearSlot(lat, lng);

		return slots;
			
		// trattandosi di una chiamata AJAX metto i valori di ritorno dentro response e
		// NON richiamo la view
	}

}