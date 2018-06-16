package main.view;

import main.controller.Request;
import main.dto.ManagementCarPlaceDTO;
import main.model.Car;
import main.model.Carplace;
import main.model.Payment;
import main.model.Stop;


import java.util.*;

public class ManagementCarPlaceView implements View {

    private List<Carplace> carplace;
    private List<ManagementCarPlaceDTO> managementCarPlaceDTOs;


    @Override
    public void showResults(Request request) {
        this.carplace = (List<Carplace>) request.get("carplace");
        this.managementCarPlaceDTOs = (List<ManagementCarPlaceDTO>) request.get("managementCarPlaceDTOs");

        System.out.println("----- GESTIONE PARCHEGGI -----");
        System.out.println("");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        System.out.format("| ID PARCHEGGIO     | ID SLOT   | TIPO DISABILE   | OCCUPATO    | TARGA    | START                     | FINISH                    | PAGATO     |%n");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        String leftAlignFormat = "| %-17d | %-9d | %-15s | %-11s | %-8s | %-25s | %-25s | %-10s |%n";

        for(ManagementCarPlaceDTO managementCarPlaceDTOs: this.managementCarPlaceDTOs){
            System.out.format(leftAlignFormat, managementCarPlaceDTOs.getCarplace().getId_carplace(), managementCarPlaceDTOs.getCarplace().getId_slot(), managementCarPlaceDTOs.getCarplace().getType(), managementCarPlaceDTOs.getCarplace().isBusy(), managementCarPlaceDTOs.getCar().getLicensePlate(), managementCarPlaceDTOs.getStop().getStart(), managementCarPlaceDTOs.getStop().getFinish(), "BO");
            System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        }
        for(Carplace place: this.carplace){
            if(!place.isBusy()){
                System.out.format(leftAlignFormat, place.getId_carplace(), place.getId_slot(), place.getType(), "false", "", "", "", "");
                System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
            }
        }
    }

    @Override
    public void showOptions() {
        System.out.println("//TODO: Eventuale menu");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {
        System.out.println("//TODO: decidere dove andare qui");
        getInput();
    }
}
