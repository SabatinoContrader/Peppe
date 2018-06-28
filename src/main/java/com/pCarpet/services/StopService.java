package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pCarpet.dao.CarPlaceRepository;
import com.pCarpet.dao.CarRepository;
import com.pCarpet.dao.ReportRepository;
import com.pCarpet.dao.StopRepository;
import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.dto.ManagementExtensionStopDTO;
import com.pCarpet.model.Car;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Report;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

@Service
public class StopService {

    private StopRepository stopRepository;
    private CarPlaceService carPlaceService;
    private CarService carService;
    private SlotService slotService;
    
    @Autowired 
    public  StopService(StopRepository stopRepository, CarPlaceService carPlaceService, CarService carService, SlotService slotService) {
        this.stopRepository = stopRepository;
        this.carPlaceService = carPlaceService;
        this.carService = carService;
        this.slotService = slotService;

    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(User user)
    {
        List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new ArrayList<ManagementExtensionStopDTO>();
        
        List<Car> usercars = carService.getAllCar(user);
        for (Car car : usercars) {
            Stop stop = this.stopRepository.findByCar(car);
            if (stop != null) {
                Carplace carplace = carPlaceService.getCarplace(stop.getCarplace().getId());
                Slot slot = slotService.getSlot(carplace.getSlot().getId());
                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
                managementExtensionStopDTOs.add(managementExtensionStopDTO);
            }
        }
        return managementExtensionStopDTOs;
    }
    
    public void extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO)
    {
        int id_stop = managementExtensionStopDTO.getId_stop();
        //il finish contiene già la data aggiornata
        String finish = managementExtensionStopDTO.getFinish();
        this.stopRepository.extensionStop(id_stop,finish);
    }
    
    public List<ManagementCarPlaceDTO> getAllStop(Slot slot) {
    	List<Carplace> allCarPlace = carPlaceService.getAllCarPlace(slot);
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new ArrayList<ManagementCarPlaceDTO>();
        
        for (Carplace place : allCarPlace) {
            Stop stop = getStop(place);
            if (stop != null) {
                Car car = carService.getCar(stop.getCar().getId());
                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(place, stop, car);
                managementCarPlaceDTOs.add(managementCarPlaceDTO);
            } else {
                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(place);
                managementCarPlaceDTOs.add(managementCarPlaceDTO);
            }
        }
        return managementCarPlaceDTOs;
    }
    
    public Stop getStop(Carplace carplace) {
        return this.stopRepository.findByCarplace(carplace);
    }
}
