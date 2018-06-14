package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Report;
import sun.applet.Main;

import java.util.List;
import java.util.Scanner;

public class ReportHystoryView implements View {

    private List<Report> reports;

    public void showResults(Request request) {

        this.reports = (List<Report>) request.get("reports");

    }


    public void showOptions()
    {
        for ( Report report : this.reports )
        {
            System.out.println("Report type: " + report.getType()  + " Report description: " + report.getDescription() );
        }

    }

    public void submit() {
        //add back choice
        MainDispatcher.getInstance().callAction("Report", "doControl", null);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}