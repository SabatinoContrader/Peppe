package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pCarpet.model.Slot;

@Repository
@Transactional
public interface SlotRepository extends CrudRepository<Slot, Long>{
	
	List<Slot> findAll();
	
	//@Modifying
	//@Query("SELECT S FROM Slot S WHERE S.id_slot=?1")
	
	Slot findById(int id);
	
	//List<Slot> getNearSlots... 
	
}