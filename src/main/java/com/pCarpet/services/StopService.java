package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.StopRepository;
import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.dto.ManagementExtensionStopDTO;
import com.pCarpet.model.Car;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

@Service
public class StopService {

    private StopRepository stopRepository;
    private CarService carService;
    private SlotService slotService;
    
    @Autowired 
    public  StopService(StopRepository stopRepository, CarService carService, SlotService slotService) {
        this.stopRepository = stopRepository;
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
                Slot slot = slotService.getSlot(stop.getSlot().getId());
                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
                managementExtensionStopDTOs.add(managementExtensionStopDTO);
            }
        }
        return managementExtensionStopDTOs;
    }
    
    public void extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO)
    {
        int id_stop = managementExtensionStopDTO.getId_stop();
        String finish = managementExtensionStopDTO.getFinish();
        this.stopRepository.extensionStop(id_stop,finish);
    }
    
    public List<ManagementCarPlaceDTO> getAllStop(Slot slot) {
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new ArrayList<ManagementCarPlaceDTO>();
        
        List<Stop> stops = getStops(slot);
        
        for (Stop stop : stops) {
            	Car car = carService.getCar(stop.getCar().getId());
                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(stop, car);
                managementCarPlaceDTOs.add(managementCarPlaceDTO);
        }
        return managementCarPlaceDTOs;
    }
    
    public List<Stop> getStops(Slot slot) {
        return this.stopRepository.findBySlot(slot);
    }
    
    public void insertStop(Stop stop)
    {
    	this.stopRepository.save(stop);
    }
}
