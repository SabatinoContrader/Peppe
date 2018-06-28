package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pCarpet.model.Car;
import com.pCarpet.model.User;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long>{
	
	List<Car> findByUser(User user);
	
	void deleteById(int id);
	
	Car save(Car car);
	
	Car findById(int id);

	
}
