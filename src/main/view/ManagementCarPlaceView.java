package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.ManagementCarPlaceDTO;
import main.model.Car;
import main.model.Carplace;
import main.model.Payment;
import main.model.Stop;


import java.util.*;

public class ManagementCarPlaceView implements View {

    private List<ManagementCarPlaceDTO> managementCarPlaceDTOs;
    private int choice;
    private int id_slot;


    @Override
    public void showResults(Request request) {
        this.id_slot = Integer.parseInt(request.get("id_slot").toString());
        this.managementCarPlaceDTOs = (List<ManagementCarPlaceDTO>) request.get("managementCarPlaceDTOs");

        System.out.println("----- GESTIONE PARCHEGGI -----");
        System.out.println("");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        System.out.format("| ID PARCHEGGIO     | ID SLOT   | TIPO DISABILE   | OCCUPATO    | TARGA    | START                     | FINISH                    | PAGATO     |%n");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        String leftAlignFormat = "| %-17d | %-9d | %-15s | %-11s | %-8s | %-25s | %-25s | %-10s |%n";

        for(ManagementCarPlaceDTO managementCarPlaceDTOs: this.managementCarPlaceDTOs){
            System.out.format(leftAlignFormat, managementCarPlaceDTOs.getId_carplace(), managementCarPlaceDTOs.getId_slot(), managementCarPlaceDTOs.isType(), managementCarPlaceDTOs.isBusy(), managementCarPlaceDTOs.getLicense_plate(), managementCarPlaceDTOs.getStart(), managementCarPlaceDTOs.getFinish(), "BO");
            System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        }
    }

    @Override
    public void showOptions() {
        System.out.println("");
        System.out.println("1) Torna alla Home");
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

        if (choice == 1) {
            MainDispatcher.getInstance().callAction("Home", "doControl", request);
        } else {
            request.put("managementCarPlaceDTOs", managementCarPlaceDTOs);
            request.put("id_slot", id_slot);
            MainDispatcher.getInstance().callAction("ManagementCarPlace", "doControl", request);
        }


    }
}
