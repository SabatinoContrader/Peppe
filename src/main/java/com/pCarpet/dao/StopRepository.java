package com.pCarpet.dao;


import com.pCarpet.model.Car;
import com.pCarpet.model.Carplace;
import com.pCarpet.model.Stop;

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
	
	Stop findByCarplace(Carplace carplace);
	
	Stop save(Stop stop);
}