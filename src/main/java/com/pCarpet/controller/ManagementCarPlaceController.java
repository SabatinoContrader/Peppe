package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.model.Slot;
import com.pCarpet.services.SlotService;
import com.pCarpet.services.StopService;

@Controller
@RequestMapping("/ManagementCarPlace")
public class ManagementCarPlaceController {

	private SlotService slotService;
	private StopService stopService;

	@Autowired
	public ManagementCarPlaceController(SlotService slotService, StopService stopService) {

		this.slotService = slotService;
		this.stopService = stopService;
	}

	@RequestMapping(value = "/CarPlace", method = RequestMethod.GET)
	public String getAllCarplace(HttpServletRequest request, Model model) {

		int id_slot = Integer.parseInt(request.getParameter("id"));
		Slot slot = slotService.getSlot(id_slot);
		List<ManagementCarPlaceDTO> managementCarPlaceDTOs = stopService.getAllStop(slot);
		model.addAttribute("managementCarPlaceDTO", managementCarPlaceDTOs);
		return "managementCarPlace";

	}


}