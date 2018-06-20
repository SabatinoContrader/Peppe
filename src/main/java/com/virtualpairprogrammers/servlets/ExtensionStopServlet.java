package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.dto.ManagementExtensionStopDTO;
import com.virtualpairprogrammers.services.StopService;
import com.virtualpairprogrammers.services.UserService;

public class ExtensionStopServlet extends HttpServlet {

  private UserService userService;
  private StopService stopService;

  public ExtensionStopServlet() {
    userService = new UserService();
    stopService = new StopService();
  }

  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = userService.getLoggedUser();
    List<ManagementExtensionStopDTO> managementExtensionStopDTO;

    String richiesta = request.getParameter("richiesta");

    if (richiesta.equalsIgnoreCase("home")) {
      managementExtensionStopDTO = stopService.getAllExtensionStop(user.getUsername());
      request.setAttribute("stops", managementExtensionStopDTO);
      getServletContext().getRequestDispatcher("/extensionStops.jsp").forward(request, response);
    } else if (richiesta.equalsIgnoreCase("extendStop")) {
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

      ManagementExtensionStopDTO item = new ManagementExtensionStopDTO(id_stop, address, start, updateddate,
          name);
      stopService.extensionStop(item);
      managementExtensionStopDTO = stopService.getAllExtensionStop(user.getUsername());
      request.setAttribute("stops", managementExtensionStopDTO);
      getServletContext().getRequestDispatcher("/extensionStops.jsp").forward(request, response);
    }

  }
}