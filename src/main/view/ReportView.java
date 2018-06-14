package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class ReportView implements View  {
    private int choice;

    public void showResults(Request request) {

    }

    public void showOptions() {
        System.out.println("-------REPORT-------");
        System.out.println("");
        System.out.println("1) Invia Segnalazione");
        System.out.println("2) Torna Home");
        this.choice = Integer.parseInt(getInput());
    }

    public void submit() {
        if (choice < 1 || choice > 3)
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        else if (choice == 3)
            MainDispatcher.getInstance().callAction("Login", "doControl", null);
        else {
            Request request = new Request();
            request.put("choice", choice);
            MainDispatcher.getInstance().callAction("Gomma", "doControl", request);
        }
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
