package com.pCarpet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/Legislation")
public class LegislationController {
	
	@Autowired
	public LegislationController() {
		
	}
	
	@RequestMapping(value = "/legislations", method = RequestMethod.GET)
	public String getLegislations(HttpServletRequest request, Model model ) {
		return "legislation";
	}

}
