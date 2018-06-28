package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;


@Repository
@Transactional
public interface CarPlaceRepository extends CrudRepository<Carplace, Long>{
	
	List<Carplace> findBySlot(Slot slot);
	
	Carplace findById(int id);
}
