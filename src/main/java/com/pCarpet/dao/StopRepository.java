package com.pCarpet.dao;


import com.pCarpet.model.Car;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface StopRepository extends CrudRepository<Stop, Long>
{		

	Stop findByCar(Car car);
		
	@Modifying
	@Query("update Stop set finish = ?2 where id_stop = ?1")
	void extensionStop(int id_stop,String finish);
	
	List<Stop> findBySlot(Slot slot);
	
	Stop save(Stop stop);
	
	@Modifying
	@Query("update Stop set expired = ?2 where id_stop = ?1")
	void updateExpired(int id_stop,boolean isBefore);
	
	boolean existsByCar(Car car);
}