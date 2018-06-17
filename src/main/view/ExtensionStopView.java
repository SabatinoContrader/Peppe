package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.ManagementExtensionStopDTO;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;


import java.util.Scanner;

public class ExtensionStopView implements View {

    int choice;

    ManagementExtensionStopDTO managementExtensionStopDTO;
    String extensionStopViewName = "ExtensionStop";

    @Override
    public void showResults(Request request) {

        this.managementExtensionStopDTO = (ManagementExtensionStopDTO) request.get("managementExtensionStopDTO");

        System.out.println("----- LA MIA SOSTA -----");
        System.out.println("");

        System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");
        System.out.format("| INDIRIZZO                 | AUTO                | INIZIO                | FINE                  |%n");
        System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");
        String leftAlignFormat = "| %-25s | %-19s | %-20s | %-20s |%n";

        if (managementExtensionStopDTO.getAddress() != null) {
            System.out.format(leftAlignFormat, managementExtensionStopDTO.getAddress(), managementExtensionStopDTO.getName(), managementExtensionStopDTO.getStart(), managementExtensionStopDTO.getFinish());
            System.out.format("+---------------------------+---------------------+-----------------------+-----------------------+%n");

        } else {
            System.out.println("Non possiedi auto in sosta. Premere un tasto per tornare indietro.");
            getInput();
        }


    }

    @Override
    public void showOptions() {
        if (managementExtensionStopDTO.getAddress() != null) {
            System.out.println("Scegli di quanto prolungare la sosta");
            System.out.println("1) 30 minuti");
            System.out.println("2) 60 minuti");
            System.out.println("3) 120 minuti");
            System.out.println("4) Indietro");
            choice = Integer.parseInt(getInput());
        }

    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.put("extensionStopViewName", extensionStopViewName);

            //request.put("choice", choice);
            //request.put("id_stop", managementExtensionStopDTO.getId_stop());
            if (choice > 0 && choice < 4) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                try
                {
                    Date parsedDate = dateFormat.parse(managementExtensionStopDTO.getFinish());
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    LocalDateTime date = timestamp.toLocalDateTime();

                    if(choice == 1) {
                        LocalDateTime mydate = date.plus(30,ChronoUnit.MINUTES);
                        String updateddate = mydate.toString().replace("T", " ");
                        managementExtensionStopDTO.setFinish(updateddate);
                    }
                    if(choice == 2)
                    {
                        LocalDateTime mydate =date.plus(60,ChronoUnit.MINUTES);
                        String updateddate = mydate.toString().replace("T", " ");
                        managementExtensionStopDTO.setFinish(updateddate);
                    }
                    if(choice == 3)
                    {
                        LocalDateTime mydate = date.plus(120,ChronoUnit.MINUTES);
                        String updateddate = mydate.toString().replace("T", " ");
                        managementExtensionStopDTO.setFinish(updateddate);
                    }
                    request.put("managementExtensionStopDTO", managementExtensionStopDTO);
                    MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", request);
                }

                catch(Exception e) {};
            } else if (choice == 4) {
                MainDispatcher.getInstance().callAction("Home", "doControl", null);
            } else
                MainDispatcher.getInstance().callAction("ExtensionStop", "doControl", null);

    }
}
