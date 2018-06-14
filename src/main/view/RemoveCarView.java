package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Car;

import java.util.List;
import java.util.Scanner;

public class RemoveCarView implements View{

    private String carViewName="RemoveCar";
    private List<Car> cars;
    int id_car;
    int choice;

    public void showResults (Request request){
        this.cars  = (List<Car>) request.get("cars");
    }

    public void showOptions (){
        System.out.println("------ LE MIE AUTO ------");
        System.out.println("");

        if(!this.cars.isEmpty()){
            for(int i = 0; i < this.cars.size(); i++){
                System.out.println((i+1)+") "+ cars.get(i).getName());
            }


            System.out.println("SCEGLI AUTO DA CANCELLARE");
            choice = Integer.parseInt(getInput());
        }
        else {
            System.out.println("non ci sono auto, premere qualunque tasto per tornare indietro");
            getInput();
        }
    }

    public String getInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void submit(){

        if(!this.cars.isEmpty()) {
            Request request = new Request();
            request.put("id_car", cars.get(choice - 1).getId_car());
            request.put("carViewName", carViewName);

            MainDispatcher.getInstance().callAction("Car", "doControl", request);
        }
        else MainDispatcher.getInstance().callAction("Car", "doControl", null);
    }
}
