package com.pCarpet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;
import com.pCarpet.services.SlotService;
import com.pCarpet.services.StopService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/ManagementSlot")
public class ManagementSlotController
{

    private SlotService slotService;
    private UserService userService;
    private StopService stopService;

    @Autowired
    public ManagementSlotController(SlotService slotService, UserService userService, StopService stopService) {
        this.slotService = slotService;
        this.userService = userService;
        this.stopService = stopService;
    }
    
    @RequestMapping(value = "/ManagementSlotControl", method = RequestMethod.GET)
	public String ManagementSlotController (HttpServletRequest request, Model model){		
		
//    	Map<Slot,List<Stop>> map = new HashMap<>();
//    	
//    	User user = userService.getLoggedUser();
//        List<Slot> slots = (List<Slot>) slotService.getAllSlotByUser(user);
//        
//        for(Slot slot : slots )
//        {
//        	List<Stop> stops = stopService.getStops(slot);
//        	map.put(slot, stops);
//        }
    	List<ManagementCarPlaceDTO>  ManagementCarPlaceDTO = stopService.getAllStopDTOByCurrentUser();
        
        model.addAttribute("ManagementCarPlaceDTO", ManagementCarPlaceDTO);
        //model.addAttribute("slots", slots);
        return "managementSlot";
	   
	}
}