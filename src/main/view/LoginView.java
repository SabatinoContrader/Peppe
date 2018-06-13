package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class LoginView implements View {

    private int choice;

    private String nomeUtente;
    private String password;

    public void showResults (Request request) {

    }


    public void showOptions () {

        System.out.println("Benvenuto in pCarpet");
        System.out.println("");
        System.out.println("");
        System.out.println("-------MENU-------");
        System.out.println("");
        System.out.println("1) Esegui il login");
        System.out.println("2) Iscriviti");
        this.choice = Integer.parseInt(getInput());


    }

    public void submit() {

        if(choice == 1)
        {
            System.out.println("-----LOGIN----");
            System.out.println("Nome utente:");
            nomeUtente = getInput();
            System.out.println("Password:");
            password = getInput();

            Request request = new Request();
            request.put("nomeUtente", nomeUtente);
            request.put("password", password);

            MainDispatcher.getInstance().callAction("Home", "doControl", request);
        }
        else if (choice == 2)
        {
            MainDispatcher.getInstance().callAction("Signup", "doControl", null);
        }

    }


    public String getInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected void send () {
    }


}
