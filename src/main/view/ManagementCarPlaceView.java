package main.view;

import main.controller.Request;
import main.model.Car;
import main.model.Carplace;
import main.model.Payment;
import main.model.Stop;


import java.util.*;

public class ManagementCarPlaceView implements View {

    private int choice;
    private List<Carplace> carplace;
    private Map<Integer, Stop> stops;
    private Map<Integer, Car> cars;
    private Map<Integer, Payment> payments;


    @Override
    public void showResults(Request request) {
        this.carplace = (List<Carplace>) request.get("carplace");
        this.stops = (HashMap<Integer, Stop>) request.get("stops");
        this.cars = (HashMap<Integer, Car>) request.get("cars");
        this.payments = (HashMap<Integer, Payment>) request.get("payments");

        System.out.println("----- GESTIONE PARCHEGGI -----");
        System.out.println("");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        System.out.format("| ID PARCHEGGIO     | ID SLOT   | TIPO DISABILE   | OCCUPATO    | TARGA    | START                     | FINISH                    | PAGATO     |%n");
        System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
        String leftAlignFormat = "| %-17d | %-9d | %-15s | %-11s | %-8s | %-25s | %-25s | %-10s |%n";

        for(Carplace place: this.carplace){
            String output = "";
            if(stops.containsKey(place.getId_carplace())) {
                Stop stop = stops.get(place.getId_carplace());
                Car car = cars.get(place.getId_carplace());
                String start = stop.getStart();
                String finish = stop.getFinish();
                String licensePlate = car.getLicensePlate();
                String paid;
                if(payments.containsKey(stop.getId_stop()))
                    paid = "SI";
                else
                    paid = "NO";

                System.out.format(leftAlignFormat, place.getId_carplace(), place.getId_slot(), place.getType(), "SI", licensePlate, start, finish, paid);

            }
            else{

                System.out.format(leftAlignFormat, place.getId_carplace(), place.getId_slot(), place.getType(), "NO", "", "", "", "");

            }
            System.out.format("+-------------------+-----------+-----------------+-------------+----------+---------------------------+---------------------------+------------+%n");
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
