package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.PaymentRepository;
import com.pCarpet.model.Payment;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

@Service
public class PaymentService {

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public void insertPayment(Payment payment) {
		this.paymentRepository.save(payment);
	}

    public List<Payment> getAllPayment(User user) {
    	return this.paymentRepository.findByUser(user);
    }
    
    public List<Payment> getPaymentForStop(Stop stop) {
    	return this.paymentRepository.findByStop(stop);
    }
}