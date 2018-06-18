package main.controller;

import main.MainDispatcher;
import main.model.Report;
import main.model.User;
import main.service.LoginService;
import main.service.ReportService;

import java.util.List;

public class ReportController implements Controller {

    private ReportService reportService;
    private LoginService loginService;

    public ReportController() {
        reportService = new ReportService();
        loginService = new LoginService();
    }

    public void doControl(Request request) {
        String username = "";
        User user = loginService.getLoggedUser();
        username = user.getUsername();
        if(user.getType().equalsIgnoreCase("driver")) {
            if (request != null) {
                String reportViewName = request.get("reportViewName").toString();
                if (reportViewName.equals("Report")) {
                    int choice = Integer.parseInt(request.get("choice").toString());

                    if (choice == 1) {
                        MainDispatcher.getInstance().callView("ReportSend", null);
                    } else if (choice == 2) {
                        List<Report> reports = reportService.getAllReportModels(username, false);
                        Request hystory_request = new Request();
                        hystory_request.put("reports", reports);
                        MainDispatcher.getInstance().callView("ReportHystory", hystory_request);
                    }
                } else if (reportViewName.equals("ReportSend")) {
                    int type = Integer.parseInt(request.get("type").toString());
                    String description = request.get("description").toString();
                    String time = request.get("time").toString();
                    Report report = new Report(type, description, time, username);
                    reportService.insertReport(report);
                    request = new Request();
                    request.put("username", username);
                    MainDispatcher.getInstance().callView("Report", request);
                }
            } else {
                request = new Request();
                request.put("username", username);
                MainDispatcher.getInstance().callView("Report", request);
            }

        } else if(user.getType().equalsIgnoreCase("gestore")) {
            List<Report> reports = reportService.getAllReportOwner();
            Request owner_request = new Request();
            owner_request.put("reports", reports);
            MainDispatcher.getInstance().callView("ReportOwner", owner_request);
        }
    }
}