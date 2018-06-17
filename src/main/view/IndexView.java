package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class IndexView implements View {

    private int choice;
    private String nomeUtente;
    private String password;

    @Override
    public void showResults(Request request) {
        if(request != null) {
            System.out.println("Username o password sbagliati");
            System.out.println("");
        }
    }

    @Override
    public void showOptions() {

        System.out.println("Benvenuto in pCarpet");
        System.out.println("");
        System.out.println("----- MENU -----");
        System.out.println("");
        System.out.println("1) Esegui il login");
        System.out.println("2) Iscriviti");
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

        if (choice == 1)
            MainDispatcher.getInstance().callAction("Login", "doControl", null);
        else if (choice == 2)
            MainDispatcher.getInstance().callAction("Signup", "doControl", null);
        else
            MainDispatcher.getInstance().callAction("Index", "doControl", null);


    }

}
