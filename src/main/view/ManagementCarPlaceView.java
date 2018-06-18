package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.ManagementCarPlaceDTO;

import java.util.*;

public class ManagementCarPlaceView implements View {

    private List<ManagementCarPlaceDTO> managementCarPlaceDTOs;
    private int choice;
    private int id_slot;


    @Override
    public void showResults(Request request) {
        this.id_slot = Integer.parseInt(request.get("id_slot").toString());
        this.managementCarPlaceDTOs = (List<ManagementCarPlaceDTO>) request.get("managementCarPlaceDTOs");
        System.out.println("");
        System.out.println("----- GESTIONE PARCHEGGI -----");
        System.out.println("");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        System.out.format("| ID PARCHEGGIO     | ID SLOT   | TIPO DISABILE   | OCCUPATO    | TARGA    | START                     | FINISH                    | PAGATO     |%n");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        String leftAlignFormat = "| %-17d | %-9d | %-15s | %-11s | %-8s | %-25s | %-25s | %-10s |%n";

        for (ManagementCarPlaceDTO managementCarPlaceDTOs : this.managementCarPlaceDTOs) {
            System.out.format(leftAlignFormat, managementCarPlaceDTOs.getId_carplace(), managementCarPlaceDTOs.getId_slot(), managementCarPlaceDTOs.isType(), managementCarPlaceDTOs.isBusy(), managementCarPlaceDTOs.getLicense_plate(), managementCarPlaceDTOs.getStart(), managementCarPlaceDTOs.getFinish(), "");
            System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        }
    }

    @Override
    public void showOptions() {
        System.out.println("");
        System.out.println("Premere invio per tornare indietro.");
        getInput();
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {
        MainDispatcher.getInstance().callAction("Home", "doControl", null);
    }
}
