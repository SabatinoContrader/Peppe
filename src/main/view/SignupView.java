package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.time.LocalDate;
import java.util.Scanner;

public class SignupView implements View {

    private String username;
    private String password;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String birthplace;
    private String address;
    private Boolean handicapped;

    @Override
    public void showResults(Request request) {

    }

    @Override
    public void showOptions() {
        System.out.println("");
        System.out.println("----- INSERISCI LE TUE INFORMAZIONI -----");
        System.out.println("Username:");
        username = getInput();
        System.out.println("Password:");
        password = getInput();
        type = "driver";
        System.out.println("Name:");
        name = getInput();
        System.out.println("Surname:");
        surname = getInput();
        System.out.println("Birthdate (please insert yyyy-MM-dd format):");
        birthdate = toLocalDate(getInput());
        System.out.println("Birthplace:");
        birthplace = getInput();
        System.out.println("Address:");
        address = getInput();
        System.out.println("Handicapped (please insert 0->false, 1->true):");
        handicapped = stringToBool(getInput());
        System.out.println("-----GRAZIE,OPERAZIONE IN CORSO----");
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
        request.put("type", type);
        request.put("name", name);
        request.put("surname", surname);
        request.put("birthdate", birthdate);
        request.put("birthplace", birthplace);
        request.put("address", address);
        request.put("handicapped", handicapped);

        MainDispatcher.getInstance().callAction("Signup", "doControl", request);
    }


    private LocalDate toLocalDate(String date) {
        while (date.length() != 10) {
            System.out.println("Incorrect format please reinsert password:");
            System.out.println("");
            System.out.println("Birthdate (please insert yyyy-MM-dd format):");
            date = getInput();
        }

        date.replace("_", "-");
        date.replace(" ", "-");
        date.replace("/", "-");

        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    private boolean stringToBool(String s) {
        if (s.equals("1"))
            return true;
        if (s.equals("0"))
            return false;
        throw new IllegalArgumentException(s + " is not a bool. Only 1 and 0 are.");
    }

}