package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.ManagementExtensionStopDTO;

import java.util.List;
import java.util.Scanner;

public class ExtensionStopsView implements View {

    int choice;

    List<ManagementExtensionStopDTO> managementExtensionStopDTO;
    String extensionStopViewName = "ExtensionStops";

    @Override
    public void showResults(Request request) {

        this.managementExtensionStopDTO = (List<ManagementExtensionStopDTO>) request.get("managementExtensionStopDTO");

        System.out.println("----- LA MIA SOSTA -----");
        System.out.println("");

        System.out.format("+-----+---------------------------+---------------------+-----------------------+-----------------------+%n");
        System.out.format("|     | INDIRIZZO                 | AUTO                | INIZIO                | FINE                  |%n");
        System.out.format("+-----+---------------------------+---------------------+-----------------------+-----------------------+%n");
        String leftAlignFormat = "| %-3d | %-25s | %-19s | %-20s | %-20s |%n";
        int i = 1;

        for (ManagementExtensionStopDTO item : this.managementExtensionStopDTO) {
            System.out.format(leftAlignFormat, i, item.getAddress(), item.getName(), item.getStart(), item.getFinish());
            System.out.format("+-----+---------------------------+---------------------+-----------------------+-----------------------+%n");
        }


    }

    @Override
    public void showOptions() {
        System.out.println("Scegli di quale auto prolungare la sosta");
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
        request.put("managementExtensionStopDTO", managementExtensionStopDTO.get(choice - 1));
        request.put("extensionStopViewName", extensionStopViewName);

        MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", request);
    }
}