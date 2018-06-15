package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Stop;

import java.util.Scanner;

public class ExtensionStopView implements View{

    private Stop current_stop;
    int choice;


    @Override
    public void showResults(Request request) {

        //this.current_stop = request.get("current_stop");

        System.out.println("----- LA MIA SOSTA -----");
        System.out.println("");

        // if (!this.current_stop.equals(null)) {
        System.out.format("+-------------------+------------------+-----------------+%n");
        System.out.format("| INDIRIZZO         | INIZIO           | FINE            |%n");
        System.out.format("+-------------------+------------------+-----------------+%n");
        String leftAlignFormat = "| %-16s | %-16t | %-16t |%n";


        // System.out.format(leftAlignFormat, current_stop.getId_carplace(), current_stop.getStart(), current_stop.getFinish());
        System.out.format("+-------------------+------------------+-----------------+%n");


        //} else
        // System.out.println("Non sei in sosta");


    }

    @Override
    public void showOptions() {

        System.out.println("Scegli di quanto prolungare la sosta");
        System.out.println("1) 30 minuti");
        System.out.println("2) 60 minuti");
        System.out.println("3) 120 minuti");
        System.out.println("4) Indietro");
        choice = Integer.parseInt(getInput());

    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void submit() {

        Request request = new Request();
        request.put("choice", choice);
        if (choice > 0 && choice < 5) {
            MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", request);
        } else {
            MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", null);
        }

    }
}
