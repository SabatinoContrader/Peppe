package main.controller;

import javafx.scene.paint.Stop;
import main.MainDispatcher;
import main.dao.StopDAO;
import main.dto.ManagementExtensionStopDTO;
import main.model.User;
import main.service.LoginService;
import main.service.StopService;
import main.view.ExtensionStopView;

import java.sql.Time;
import java.util.List;

public class ExtensionStopController implements Controller{

    private LoginService loginService;
    private StopService stopService;

    public ExtensionStopController() {
        loginService = new LoginService();
        stopService = new StopService();
    }

    public void doControl(Request request) {
        User loggedtuser = loginService.getLoggedUser();
        List<ManagementExtensionStopDTO> managementExtensionStopDTO = stopService.getAllExtensionStop(loggedtuser.getUsername());

        Request request_extension = new Request();
        request_extension.put("managementExtensionStopDTO",managementExtensionStopDTO);
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
            MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
    }


}
