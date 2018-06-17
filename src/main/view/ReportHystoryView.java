package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Report;

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
        System.out.format("+-----------------------+-----------------------+------------------------------------------------------------------------------+%n");
        System.out.format("| DATA E ORA            | TIPO                  | DESCRIZIONE                                                                  |%n");
        System.out.format("+-----------------------+-----------------------+------------------------------------------------------------------------------+%n");
        String leftAlignFormat = "| %-21s | %-21s | %-76s |%n";
        for (Report report : this.reports) {
            int type = report.getType();
            String description = report.getDescription();
            String typeString = "";
            String time = report.getTime();
            if (type == 1)
                typeString = "Abuso posto disabili";
            else if (type == 2)
                typeString = "Disservizio stradale";
            else
                typeString = "Problema servizio";


            System.out.format(leftAlignFormat, time, typeString, description);
            System.out.format("+-----------------------+-----------------------+------------------------------------------------------------------------------+%n");

        }
        if (reports.isEmpty()) {
            System.out.println("Nessuna segnalazione inviata. Premere un tasto per tornare indietro.");
            getInput();
        }
    }

    public void submit() {
        MainDispatcher.getInstance().callAction("Report", "doControl", null);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}