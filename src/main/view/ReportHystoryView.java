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


    public void showOptions() {
        System.out.println("----- CRONOLOGIA SEGNALAZIONI -----");
        System.out.println("");
        for ( Report report : this.reports )
        {
            int type = report.getType();
            String description = report.getDescription();
            String typeString = "";
            if(type == 1)
                typeString = "Abuso posto disabili";
            else if (type == 2)
                typeString = "Disservizio stradale";
            else
                typeString = "Problema servizio";
            System.out.println("TIPO: " + typeString  + " DESCRIZIONE: " + report.getDescription() );
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