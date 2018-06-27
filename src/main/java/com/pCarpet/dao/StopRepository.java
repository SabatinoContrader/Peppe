package com.pCarpet.dao;


import com.pCarpet.dto.ManagementExtensionStopDTO;
import com.pCarpet.model.Car;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

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
//	Report findByUserUsername(String username);
//	
//	Report save(Report report);	
//	
//	List<Report> findAll();
//	
//	List<Report> findByUser(User user);
	
	Stop findByCar(Car car);
		
	@Modifying
	@Query("update Stop set finish = ?2 where id_stop = ?1")
	Stop extensionStop(int id_stop,String finish);
	
//	@Modifying
//	@Query("Select s.id_stop, sl.address, s.start, s.finish, ca.name from stop s, slot sl, carplace c, car ca where s.id_car=ca.id_car and ca.username=?1 and c.id_slot=sl.id_slot and c.id_carplace=s.id_carplace")
//	List<ManagementExtensionStopDTO> getAllExtensionStop(String username);
	
//	@Modifying
//  @Query("SELECT r FROM Report r WHERE r.user.username=?1")
//	List<Report> findAllByUsername(String username);
}