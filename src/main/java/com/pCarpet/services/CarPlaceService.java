package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.CarPlaceRepository;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;



@Service
public class CarPlaceService {
	
	private CarPlaceRepository carPlaceRepository;
	
	@Autowired 
	public CarPlaceService(CarPlaceRepository carPlaceRepository) {
		this.carPlaceRepository = carPlaceRepository;
	}
	

	public List<Carplace> getAllCarPlace(Slot slot){
		return this.carPlaceRepository.findBySlot(slot);
	}

    public Carplace getCarplace(int id_carplace) {
        return this.carPlaceRepository.findById(id_carplace);
    }
	

}
