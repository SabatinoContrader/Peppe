package com.pCarpet.controller;

import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.model.Slot;
import com.pCarpet.services.SlotService;
import com.pCarpet.services.StopService;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MarkerController {

	SlotService slotService;
	StopService stopService;

	@Autowired
	public MarkerController(SlotService slotService, StopService stopService) {
		this.slotService = slotService;
		this.stopService = stopService;
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
	
	@PostMapping(value = "/updateSlots")
	public List<ManagementCarPlaceDTO> ManagementSlotController (HttpServletRequest request, Model model){		
		
    	List<ManagementCarPlaceDTO>  ManagementCarPlaceDTOs = stopService.getAllStopDTOByCurrentUser();
        
        //model.addAttribute("ManagementCarPlaceDTO", ManagementCarPlaceDTO);
        //model.addAttribute("slots", slots);
        return ManagementCarPlaceDTOs;
	   
	}

}