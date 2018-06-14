package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class HomeOwnerView implements View {
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
        System.out.println("-------MENU OWNER ("+username+")-------");
        System.out.println("");
        System.out.println("1) Inserisci gomma");
        System.out.println("2) Visualizza gomme disponibili");
        System.out.println("3) Logout");
        this.choice = Integer.parseInt(getInput());
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void submit() {
        if (choice < 1 || choice > 3)
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        else if (choice == 3)
            MainDispatcher.getInstance().callAction("Index", "doControl", null);
        else {
            Request request = new Request();
            request.put("choice", choice);
            MainDispatcher.getInstance().callAction("Gomma", "doControl", request);
        }
    }


}
