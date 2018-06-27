package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.ReportRepository;
import com.pCarpet.dao.StopRepository;
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

    @Autowired 
    public  StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(User user) 
    {
        List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new ArrayList<ManagementExtensionStopDTO>();
        
        //TODO: FAREEEEE!!!
//        List<Car> usercars = carService.getAllCar(user);
//        for (Car car : usercars) {
//            Stop stop = this.stopRepository.findByCar(car);
//            if (stop != null) {
//                Carplace carplace = carPlaceService.getCarplace(stop.getId_carplace());
//                Slot slot = slotService.getSlot(carplace.getId_slot());
//                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
//                managementExtensionStopDTOs.add(managementExtensionStopDTO);
//            }
//        }
        return managementExtensionStopDTOs;
    }
    
    public Stop extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO)
    {
        int id_stop = managementExtensionStopDTO.getId_stop();
        //il finish contiene già la data aggiornata
        String finish = managementExtensionStopDTO.getFinish();
        return this.stopRepository.extensionStop(id_stop,finish);
    }
}
