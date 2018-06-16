package main.service;

import main.dao.StopDAO;
import main.dto.ManagementCarPlaceDTO;
import main.dto.ManagementExtensionStopDTO;
import main.model.Car;
import main.model.Carplace;
import main.model.Stop;

import java.util.ArrayList;
import java.util.List;

public class StopService {
    private StopDAO stopDAO;
    private CarPlaceService carPlaceService;
    private CarService carService;

    public StopService() {
        this.stopDAO = new StopDAO();
        this.carPlaceService = new CarPlaceService();
        this.carService = new CarService();
    }

    public List<ManagementCarPlaceDTO> getAllStop(int id_slot) {
        List<Carplace> allCarPlace = carPlaceService.getAllCarPlace(id_slot);
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new ArrayList<ManagementCarPlaceDTO>();

        for (Carplace place : allCarPlace) {
            Stop stop = getStop(place.getId_carplace());
            if (stop != null) {
                Car car = carService.getCar(stop.getId_car(), false);
                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(place, stop, car);
                managementCarPlaceDTOs.add(managementCarPlaceDTO);
            }
        }
        return managementCarPlaceDTOs;
    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(String username) {
        return this.stopDAO.getAllExtensionStop(username);
    }

    public boolean extensionStop(int minute, int id_slot) {
        return this.stopDAO.extensionStop(minute, id_slot);
    }

    public Stop getStop(int id_carplace) {
        return this.stopDAO.getStop(id_carplace);
    }

}
