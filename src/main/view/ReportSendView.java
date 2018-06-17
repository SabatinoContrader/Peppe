package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.sql.Timestamp;
import java.util.Scanner;

public class ReportSendView implements View {

    private String description;
    private int type;

    private String reportViewName = "ReportSend";

    public void showResults(Request request) {

    }


    public void showOptions() {
        System.out.println("----- COMPILA LA TUA SEGNALAZIONE -----");
        System.out.println("1) Abuso spazio dedicato a persone con disabilità");
        System.out.println("2) Disservizio stradale");
        System.out.println("3) Problema riscontrato nell'usufruire del servizio");
        type = Integer.parseInt(getInput());
        System.out.println("Descrivere il problema in un breve testo: ");
        description = getInput();
    }

    public void submit() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Request request = new Request();
        request.put("type", type);
        request.put("description", description);
        request.put("time", time);
        request.put("reportViewName", reportViewName);
        MainDispatcher.getInstance().callAction("Report", "doControl", request);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}