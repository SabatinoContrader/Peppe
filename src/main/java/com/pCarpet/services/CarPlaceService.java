package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.CarPlaceRepository;
import com.pCarpet.dao.CarRepository;
import com.pCarpet.model.Car;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;
import com.pCarpet.model.User;
import com.pCarpet.utils.Reference;


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

	

}
