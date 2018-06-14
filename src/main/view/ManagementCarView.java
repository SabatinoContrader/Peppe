package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class ManagementCarView implements View{

    private int choice;
    private String carViewName="ManagementCar";

    public void showResults(Request request){
        //vuoto?
    }


    public void showOptions(){

        System.out.println("------ MANAGE AUTO ------");
        System.out.println("1) Aggiungi auto");
        System.out.println("2) Elimina auto");
        System.out.println("3) Indietro");
        choice = Integer.parseInt(getInput());
    }


    public String getInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public void submit() {
        Request request = new Request();
        request.put("carViewName", carViewName);
        request.put("choice", choice);
        if (choice > 0 && choice < 4) {
            MainDispatcher.getInstance().callAction("Car", "doControl", request);
        } else {
            MainDispatcher.getInstance().callAction("Car", "doControl", null);
        }
    }
}
