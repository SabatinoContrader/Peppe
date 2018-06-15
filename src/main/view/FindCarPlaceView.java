package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class FindCarPlaceView implements View {

    private int choice;
    private String username;
    private String place;

    @Override
    public void showResults(Request request) {
        username = (String) request.getString("username");
    }

    @Override
    public void showOptions() {
        System.out.println("----- TROVA PARCHEGGIO ("+username+") -----");
        System.out.println("");
        System.out.println("Destinazione:");
        place = getInput();

    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {

    }


}
