package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.CarRepository;
import com.pCarpet.model.Car;
import com.pCarpet.model.User;
import com.pCarpet.utils.Reference;


@Service
public class CarService {
	
	private CarRepository carRepository;
	
	@Autowired 
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
//	public boolean addcar(Car car, Reference<List<Car>> carlist)
//    {
//        boolean result = this.carDAO.addcar(car);
//        carlist.get().add(car);
//        return result;
//    }
//
	
	public void addCar(Car car){
        this.carRepository.save(car);
    }
	
	
    public void removeCar(int id_car){
        this.carRepository.deleteById(id_car);
    }



    public List<Car> getAllCar(User user) {
    	return this.carRepository.findByUser(user);
    }

    public Car getCar(int id_car){
        return this.carRepository.findById(id_car);
    }

}
