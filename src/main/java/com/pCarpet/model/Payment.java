package com.pCarpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data

@Entity
public class Payment {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_payment;
	
	@Column
	private float quantity;
	
	@ManyToOne
	@JoinColumn(name="username")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_slot")

	private Slot slot;

}
