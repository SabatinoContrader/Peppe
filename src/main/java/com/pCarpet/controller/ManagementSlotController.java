package com.pCarpet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.model.Slot;
import com.pCarpet.services.SlotService;

@Controller
@RequestMapping("/ManagementSlot")
public class ManagementSlotController
{

    private SlotService slotService;

    @Autowired
    public ManagementSlotController(SlotService slotService) {
        this.slotService = slotService;
    }
    
    @RequestMapping(value = "/ManagementSlotControl", method = RequestMethod.GET)
	public String ManagementSlotController (HttpServletRequest request, Model model){		
		
        List<Slot> slots = (List<Slot>) slotService.getAllSlot();
        model.addAttribute("slots", slots);
        return "managementSlot";
	   
	}
}