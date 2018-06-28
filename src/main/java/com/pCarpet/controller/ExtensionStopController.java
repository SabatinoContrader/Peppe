package com.pCarpet.controller;

import com.pCarpet.dto.ManagementExtensionStopDTO;
import com.pCarpet.model.User;
import com.pCarpet.services.UserService;
import com.pCarpet.services.StopService;

import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/ExtensionStop")
public class ExtensionStopController {

	private UserService userService;
	private StopService stopService;

	@Autowired
	public ExtensionStopController(UserService userService, StopService stopService) {
		this.userService = userService;
		this.stopService = stopService;
	}

	@RequestMapping(value = "/extensionStops", method = RequestMethod.GET)
	public String extensionStops(HttpServletRequest request, Model model) {
		User user = userService.getLoggedUser();
		List<ManagementExtensionStopDTO> managementExtensionStopDTO;
		managementExtensionStopDTO = stopService.getAllExtensionStop(user);
		model.addAttribute("stops", managementExtensionStopDTO);
		return "extensionStops";
	}

	@RequestMapping(value = "/extendStop", method = RequestMethod.POST)
	public String extendStop(HttpServletRequest request, Model model) {
		User user = userService.getLoggedUser();

		int id_stop = Integer.parseInt(request.getParameter("id").toString());
		int time = Integer.parseInt(request.getParameter("minute").toString());

		String name = request.getParameter("name");
		String start = request.getParameter("start");
		String finish = request.getParameter("finish");
		String address = request.getParameter("address");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		String updateddate = null;
		try {

			Date parsedDate = dateFormat.parse(finish);

			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			LocalDateTime date = timestamp.toLocalDateTime();
			LocalDateTime mydate = date.plus(time, ChronoUnit.MINUTES);
			updateddate = mydate.toString().replace("T", " ");

		} catch (Exception e) {
			
		}

		ManagementExtensionStopDTO item = new ManagementExtensionStopDTO(id_stop, address, start, updateddate, name);
		stopService.extensionStop(item);
		List<ManagementExtensionStopDTO> managementExtensionStopDTO = stopService.getAllExtensionStop(user);
		request.setAttribute("stops", managementExtensionStopDTO);
		return "extensionStops";
	}
}