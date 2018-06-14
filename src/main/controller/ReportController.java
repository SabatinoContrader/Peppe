package main.controller;

import main.MainDispatcher;
import main.model.Report;
import main.service.LoginService;
import main.service.ReportService;

import java.util.List;

public class ReportController implements Controller {

    private ReportService reportService;

    public ReportController() {
        reportService = new ReportService();
    }

    public void doControl(Request request) {
        if (request != null) {

            String reportViewName = request.get("reportViewName").toString();
            if(reportViewName.equals("Report"))
            {
                int choice = Integer.parseInt(request.get("choice").toString());

                if (choice == 1) {
                    MainDispatcher.getInstance().callView("ReportSend", null);
                } else if (choice == 2) {
                    //TODO: get username from userService
                    List<Report> reports = reportService.getAllReportModels("username1",true);
                    Request hystory_request = new Request();
                    hystory_request.put("reports", reports);
                    MainDispatcher.getInstance().callView("ReportHystory", hystory_request);
                }
            }
            else if (reportViewName.equals("ReportSend"))
            {
                int type = Integer.parseInt(request.get("type").toString());
                String description = request.get("description").toString();

                //TODO: get username from userService
                Report report = new Report(type,description,"username1");
                reportService.insertReport(report);
                MainDispatcher.getInstance().callView("Report", null);
            }
        }
        else MainDispatcher.getInstance().callView("Report", null);

    }
}