package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pCarpet.model.Slot;
import com.pCarpet.model.User;

@Repository
@Transactional
public interface SlotRepository extends CrudRepository<Slot, Long>{
	
	List<Slot> findAll();
	
	List<Slot> findByUser(User user);
	
	Slot findById(int id);
	
	@Modifying
	@Query("SELECT s FROM Slot s WHERE (ABS(s.latitude - ?1)) < 0.01 AND (ABS(s.longitude - ?2)) < 0.01")
	List<Slot> getNearSlot(double lat,double lng);
	
}