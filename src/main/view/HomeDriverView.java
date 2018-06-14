package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class HomeDriverView implements View {

    private int choice;
    private String username;

    @Override
    public void showResults(Request request) {
        username = (String) request.getString("username");
    }

    @Override
    public void showOptions() {
        System.out.println("Benvenuto in pCarpet");
        System.out.println("");
        System.out.println("");
        System.out.println("-------MENU DRIVER ("+username+")-------");
        System.out.println("");
        System.out.println("1) Cerca parcheggio");
        System.out.println("2) Prenota parcheggio privato");
        System.out.println("3) Prolunga sosta");
        System.out.println("4) Gestione auto");
        System.out.println("5) Gestione segnalazioni");
        System.out.println("6) Logout");
        this.choice = Integer.parseInt(getInput());
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {
        Request request = new Request();
        request.put("username", username);
        switch (choice) {
            case 1:
                MainDispatcher.getInstance().callAction("FindCarPlace", "doControl", request);
            case 2:
                MainDispatcher.getInstance().callAction("Book", "doControl", null);
            case 3:
                MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", null);
            case 4:
                MainDispatcher.getInstance().callAction("Car", "doControl", null);
            case 5:
                MainDispatcher.getInstance().callAction("Report", "doControl", null);
            case 6:
                MainDispatcher.getInstance().callAction("Index", "doControl", null);
            default:
                MainDispatcher.getInstance().callAction("Home", "doControl", null);
        }
    }


}
