package main.service;

import main.dao.StopDAO;
import main.dto.ManagementCarPlaceDTO;
import main.dto.ManagementExtensionStopDTO;
import main.model.Car;
import main.model.Carplace;
import main.model.Slot;
import main.model.Stop;

import java.util.ArrayList;
import java.util.List;

public class StopService {
    private StopDAO stopDAO;
    private CarPlaceService carPlaceService;
    private CarService carService;
    private SlotService slotService;

    public StopService() {
        this.stopDAO = new StopDAO();
        this.carPlaceService = new CarPlaceService();
        this.carService = new CarService();
        this.slotService = new SlotService();
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
            } else {
                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(place);
                managementCarPlaceDTOs.add(managementCarPlaceDTO);
            }
        }
        return managementCarPlaceDTOs;
    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(String username) {
        List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new ArrayList<ManagementExtensionStopDTO>();
        List<Car> usercars = carService.getAllCarModel(username, true);
        for (Car car : usercars) {
            int id_car = car.getId_car();
            Stop stop = getUserStop(id_car);
            if (stop != null) {
                Carplace carplace = carPlaceService.getCarplace(stop.getId_carplace());
                Slot slot = slotService.getSlot(carplace.getId_slot());
                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
                managementExtensionStopDTOs.add(managementExtensionStopDTO);
            }
        }
        return managementExtensionStopDTOs;
    }

    public boolean extensionStop(int minute, int id_stop) {
        return this.stopDAO.extensionStop(minute, id_stop);
    }

    public boolean extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO) {
        int id_stop = managementExtensionStopDTO.getId_stop();
        //il finish contiene già la data aggiornata
        String finish = managementExtensionStopDTO.getFinish();
        return this.stopDAO.extensionStop(id_stop,finish);
    }

    public Stop getStop(int id_carplace) {
        return this.stopDAO.getStop(id_carplace);
    }

    public Stop getUserStop(int id_car) {
        return this.stopDAO.getUserStop(id_car);
    }

}
