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
        System.out.println("");
        System.out.println("----- MENU OWNER (" + username + ") -----");
        System.out.println("");
        System.out.println("1) Gestione parcheggi");
        System.out.println("2) Segnalazioni degli utenti");
        System.out.println("3) Logout");
        this.choice = Integer.parseInt(getInput());
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (input.equals("")) input = scanner.nextLine();
        return input;
    }

    @Override
    public void submit() {
        if (choice < 1 || choice > 3)
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        else if (choice == 1)
            MainDispatcher.getInstance().callAction("ManagementSlot", "doControl", null);
        else if (choice == 2)
            MainDispatcher.getInstance().callAction("Report", "doControl", null);
        else {
            MainDispatcher.getInstance().callAction("Index", "doControl", null);
        }
    }


}
