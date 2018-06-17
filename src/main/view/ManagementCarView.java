package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Car;

import java.util.List;
import java.util.Scanner;

public class ManagementCarView implements View {

    private int choice;
    private String carViewName = "ManagementCar";
    private List<Car> cars;

    @Override
    public void showResults(Request request) {
        this.cars = (List<Car>) request.get("cars");

        System.out.println("----- LE MIE AUTO -----");
        System.out.println("");

        if (!this.cars.isEmpty()) {
            System.out.format("+-----+--------------+------------------+-----------------+%n");
            System.out.format("|     | TARGA        | NOME             | DIMENSIONE      |%n");
            System.out.format("+-----+--------------+------------------+-----------------+%n");
            String leftAlignFormat = "| %-3d | %-12s | %-16s | %-15s |%n";
            int i = 1;
            for (Car car : this.cars) {
                System.out.format(leftAlignFormat, i, car.getLicensePlate(), car.getName(), car.getSize());
                System.out.format("+-----+--------------+------------------+-----------------+%n");
                i++;
            }
        } else {
            System.out.println("Non ci sono auto, premere un tasto per tornare indietro");
            getInput();
        }
    }


    public void showOptions() {
        System.out.println("1) Aggiungi auto");
        System.out.println("2) Elimina auto");
        System.out.println("3) Indietro");
        choice = Integer.parseInt(getInput());
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public void submit() {
        Request request = new Request();
        request.put("carViewName", carViewName);
        request.put("choice", choice);
        if (choice > 0 && choice < 3) {
            MainDispatcher.getInstance().callAction("Car", "doControl", request);
        } else if (choice == 3) {
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        } else
            MainDispatcher.getInstance().callAction("Car", "doControl", null);

    }
}
