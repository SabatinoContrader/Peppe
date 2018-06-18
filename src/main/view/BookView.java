package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class BookView implements View {

    public void showResults(Request request) {

    }

    public void showOptions() {
        System.out.println("");
        System.out.println("----- PRENOTA SOSTE -----");
        System.out.println("");
        System.out.println("Non implementato ancora premere invio per tornare indietro");
        getInput();
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public void submit()
    {
        MainDispatcher.getInstance().callAction("Home", "doControl", null);
    }
}