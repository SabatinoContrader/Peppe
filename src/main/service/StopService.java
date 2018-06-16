package main.service;

import main.dao.StopDAO;
import main.dto.ManagementCarPlaceDTO;
import main.dto.ManagementExtensionStopDTO;
import main.model.Stop;

import java.util.List;

public class StopService {
    private StopDAO stopDAO;
    public  StopService() {
        this.stopDAO = new StopDAO();
    }

    public List<ManagementCarPlaceDTO> getAllStop(int id_slot) {
        return this.stopDAO.getAllStop(id_slot);
    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(String username) {
        return this.stopDAO.getAllExtensionStop(username);
    }

    public boolean extensionStop(int minute, int id_slot){
        return this.stopDAO.extensionStop(minute, id_slot);
    }
}
