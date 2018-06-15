package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class AddCarView implements View{

    private String carViewName="AddCar";

    private String licensePlate;
    private String name;
    private String size;

    public void showResults (Request request){
        //vuoto?
    }

    public void showOptions (){
        System.out.println("----- INSERIRE DATI AUTO -----");
        System.out.println("");
        System.out.println("TARGA: ");
        licensePlate = getInput();
        System.out.println("NOME AUTO: ");
        name = getInput();
        System.out.println("DIMENSIONE: ");
        size = getInput();

    }

    public String getInput () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public void submit(){

        Request request = new Request();
        request.put("licensePlate", licensePlate);
        request.put("name", name);
        request.put("size", size );
        request.put("carViewName", carViewName);

        MainDispatcher.getInstance().callAction("Car", "doControl", request);
    }
}
