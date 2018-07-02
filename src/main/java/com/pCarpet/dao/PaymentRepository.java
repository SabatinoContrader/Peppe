package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pCarpet.model.Car;
import com.pCarpet.model.Payment;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

@Repository
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	
	List<Payment> findByUser(User user);
	
	List<Payment> findByStop(Stop stop);
	
	Payment save(Payment payment);
	
	
}