package main.controller;

import main.MainDispatcher;
import main.dto.ManagementExtensionStopDTO;
import main.model.User;
import main.service.StopService;
import main.service.UserService;

import java.util.List;

public class ExtensionStopController implements Controller {

    private UserService userService;
    private StopService stopService;

    public ExtensionStopController() {
        userService = new UserService();
        stopService = new StopService();
    }

    public void doControl(Request request) {
        User user = userService.getLoggedUser();
        List<ManagementExtensionStopDTO> managementExtensionStopDTO;
        Request request_extension = new Request();
        if (request != null) {
            String extensionStopViewName = request.get("extensionStopViewName").toString();

            if(extensionStopViewName.equalsIgnoreCase("ExtensionStop")) {
                ManagementExtensionStopDTO ExtensionStop_DTO = (ManagementExtensionStopDTO)request.get("managementExtensionStopDTO");
                stopService.extensionStop(ExtensionStop_DTO);

                MainDispatcher.getInstance().callAction("Home", "doControl", null);

            } else if(extensionStopViewName.equalsIgnoreCase("ExtensionStops")){
                ManagementExtensionStopDTO managementSelectedExtensionStopDTO = (ManagementExtensionStopDTO)request.get("managementExtensionStopDTO");
                request_extension.put("managementExtensionStopDTO", managementSelectedExtensionStopDTO);
                MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
            }
        } else {
            managementExtensionStopDTO = stopService.getAllExtensionStop(user.getUsername());
            if (managementExtensionStopDTO.size() == 1) {
                request_extension.put("managementExtensionStopDTO", managementExtensionStopDTO.get(0));
                MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
            } else if (managementExtensionStopDTO.size() > 1) {
                request_extension.put("managementExtensionStopDTO", managementExtensionStopDTO);
                MainDispatcher.getInstance().callView("ExtensionStops", request_extension);
            } else {
                request_extension.put("managementExtensionStopDTO", new ManagementExtensionStopDTO());
                MainDispatcher.getInstance().callView("ExtensionStop", request_extension);
            }
        }
    }


}
