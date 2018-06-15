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
        System.out.format("+-----------------------+------------------------------------------------------------------------------+%n");
        System.out.format("| TIPO                  | DESCRIZIONE                                                                  |%n");
        System.out.format("+-----------------------+------------------------------------------------------------------------------+%n");

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

            String leftAlignFormat = "| %-21s | %-76s |%n";
            System.out.format(leftAlignFormat, typeString, description );
            System.out.format("+-----------------------+------------------------------------------------------------------------------+%n");
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