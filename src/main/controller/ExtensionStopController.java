package main.controller;

import javafx.scene.paint.Stop;
import main.MainDispatcher;
import main.service.StopService;
import main.view.ExtensionStopView;

import java.sql.Time;

public class ExtensionStopController implements Controller{




    //  private StopService stopService;

    //   public ExtensionStopController() {
    //     stopService = new StopService();

    // }

    public void doControl(Request request) {
        if (request != null) {

            int choice = Integer.parseInt(request.get("choice").toString());
            if (choice == 1) {
                int newFinish=30;
                //  ExtensionStopService.addTime(newFinish);
                System.out.println(newFinish);
                MainDispatcher.getInstance().callView("ExtensionStop",request);

            } else if (choice == 2) {
                int newFinish=60;
                //  ExtensionStopService.addTime(newFinish);
                MainDispatcher.getInstance().callView("ExtensionStop",request);

            } else if (choice == 3) {
                int newFinish=120;
                // ExtensionStopService.addTime(newFinish);
                MainDispatcher.getInstance().callView("ExtensionStop",request);
            } else if(choice==4)
                MainDispatcher.getInstance().callView("HomeDriver",null);


        }
        else
            MainDispatcher.getInstance().callView("ExtensionStop", null);
    }


}
