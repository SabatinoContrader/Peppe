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
        List<ManagementExtensionStopDTO> managementExtensionStopDTO;
        Request request_extension = new Request();
        if (request != null) {
            int id_stop = Integer.parseInt(request.get("id_stop").toString());
            int choice = Integer.parseInt(request.get("choice").toString());
            if (choice == 1) {
                int minute=30;
                stopService.extensionStop(minute, id_stop);
                managementExtensionStopDTO = stopService.getAllExtensionStop(loggedtuser.getUsername());
                request_extension.put("managementExtensionStopDTO",managementExtensionStopDTO);
                MainDispatcher.getInstance().callView("ExtensionStop",request_extension);

            } else if (choice == 2) {
                int minute=60;
                stopService.extensionStop(minute, id_stop);
                managementExtensionStopDTO = stopService.getAllExtensionStop(loggedtuser.getUsername());
                request_extension.put("managementExtensionStopDTO",managementExtensionStopDTO);
                MainDispatcher.getInstance().callView("ExtensionStop",request_extension);

            } else if (choice == 3) {
                int minute=120;
                stopService.extensionStop(minute, id_stop);
                managementExtensionStopDTO = stopService.getAllExtensionStop(loggedtuser.getUsername());
                request_extension.put("managementExtensionStopDTO",managementExtensionStopDTO);
                MainDispatcher.getInstance().callView("ExtensionStop",request_extension);
            }


        }
        else {
            managementExtensionStopDTO = stopService.getAllExtensionStop(loggedtuser.getUsername());
            request_extension.put("managementExtensionStopDTO",managementExtensionStopDTO);
            MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
        }
    }


}
