package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class ReportView implements View {

    private int choice;
    private String reportViewName = "Report";

    public void showResults(Request request) {

    }


    public void showOptions() {
        System.out.println("");
        System.out.println("----- SEGNALAZIONE -----");
        System.out.println("");
        System.out.println("1) Invia segnalazione");
        System.out.println("2) Leggi storico segnalazioni");
        System.out.println("3) Indietro");
        this.choice = Integer.parseInt(getInput());
    }

    public void submit() {

        if (choice != 1 && choice != 2 && choice != 3)
            MainDispatcher.getInstance().callAction("Report", "doControl", null);
        else if (choice == 1) {
            Request request = new Request();
            request.put("choice", choice);
            request.put("reportViewName", reportViewName);
            MainDispatcher.getInstance().callAction("Report", "doControl", request);
        } else if (choice == 2) {
            Request request = new Request();
            request.put("choice", choice);
            request.put("reportViewName", reportViewName);
            MainDispatcher.getInstance().callAction("Report", "doControl", request);
        } else if (choice == 3) {
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        }
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (input.equals("")) input = scanner.nextLine();
        return input;
    }

    protected void send() {
    }


}