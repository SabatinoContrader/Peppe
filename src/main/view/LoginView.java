package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class LoginView implements View {

    private int choice;

    private String username;
    private String password;


    @Override
    public void showResults(Request request) {


    }

    @Override
    public void showOptions() {
        System.out.println("----- LOGIN -----");
        System.out.println("Nome utente:");
        username = getInput();
        System.out.println("Password:");
        password = getInput();
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
        Request request = new Request();
        request.put("username", username);
        request.put("password", password);
        MainDispatcher.getInstance().callAction("Login", "doControl", request);
    }


}
