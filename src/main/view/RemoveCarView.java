package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Car;

import java.util.List;
import java.util.Scanner;

public class RemoveCarView implements View {

    private String carViewName = "RemoveCar";
    private List<Car> cars;
    int choice;

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

    @Override
    public void showOptions() {
        System.out.println("SCEGLI AUTO DA CANCELLARE");
        choice = Integer.parseInt(getInput());
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void submit() {

        if (!this.cars.isEmpty()) {
            Request request = new Request();
            request.put("id_car", cars.get(choice - 1).getId_car());
            request.put("carViewName", carViewName);

            MainDispatcher.getInstance().callAction("Car", "doControl", request);
        } else
            MainDispatcher.getInstance().callAction("Car", "doControl", null);
    }
}
