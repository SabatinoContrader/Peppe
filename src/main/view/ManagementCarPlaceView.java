package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Car;
import main.model.Carplace;
import main.model.Stop;
import main.service.CarPlaceService;

import java.util.*;

public class ManagementCarPlaceView implements View {

    private int choice;
    List<Carplace> carplace;
    Map<Integer, Stop> stops;
    Map<Integer, Car> cars;


    @Override
    public void showResults(Request request) {
        carplace = (List<Carplace>) request.get("carplace");
        stops = (HashMap<Integer, Stop>) request.get("stops");
        cars = (HashMap<Integer, Car>) request.get("cars");
    }

    @Override
    public void showOptions() {
        System.out.println("------Gestione parcheggi------");
        System.out.println("");
        //System.out.println("ID PARCHEGGIO | ID SLOT | TIPO DISABILE | OCCUPATO | DRIVER |          START          |          FINISH          | PAGATO");
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
                if(start != null)
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
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {

    }
}
