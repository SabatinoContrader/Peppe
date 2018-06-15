package main.controller;

import main.MainDispatcher;
import main.dto.ManagementCarPlaceDTO;
import main.model.Carplace;
import main.service.*;
import java.util.List;


public class ManagementCarPlaceController implements Controller {

    private CarPlaceService carPlaceService;
    private StopService stopService;

    public ManagementCarPlaceController() {
        carPlaceService = new CarPlaceService();
        stopService = new StopService();
    }

   @Override
    public void doControl(Request request) {
        int id_slot = (int)request.get("id_slot");
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = stopService.getAllStop(id_slot);
        List<Carplace> carplace = carPlaceService.getAllCarPlace(id_slot);
        request.put("carplace", carplace);
        request.put("managementCarPlaceDTOs", managementCarPlaceDTOs);
        MainDispatcher.getInstance().callView("ManagementCarPlace", request);
    }
}