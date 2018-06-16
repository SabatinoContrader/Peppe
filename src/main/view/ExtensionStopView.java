package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.ManagementExtensionStopDTO;
import main.model.Stop;

import java.util.List;
import java.util.Scanner;

public class ExtensionStopView implements View{

    private Stop current_stop;
    int choice;

    List<ManagementExtensionStopDTO> managementExtensionStopDTO;

    @Override
    public void showResults(Request request) {

        this.managementExtensionStopDTO = (List<ManagementExtensionStopDTO>)  request.get("managementExtensionStopDTO");
        //this.current_stop = request.get("current_stop");

        System.out.println("----- LA MIA SOSTA -----");
        System.out.println("");

        // if (!this.current_stop.equals(null)) {
        System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");
        System.out.format("| INDIRIZZO                 | AUTO                | INIZIO                | FINE                  |%n");
        System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");
        String leftAlignFormat = "| %-25s | %-19s | %-20s | %-20s |%n";

        for(ManagementExtensionStopDTO item : this.managementExtensionStopDTO)
        {
            System.out.format(leftAlignFormat, item.getAddress(), item.getName(), item.getStart(), item.getFinish());
            System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");
        }
        // System.out.format(leftAlignFormat, current_stop.getId_carplace(), current_stop.getStart(), current_stop.getFinish());
        //System.out.format("+-------------------+------------------+-----------------+%n");


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
        request.put("id_stop", managementExtensionStopDTO.get(0).getId_stop());
        //TODO add view to choose stop
        if (choice > 0 && choice < 4) {
            MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", request);
        } else if (choice == 4){
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        } else
            MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", null);

    }
}
