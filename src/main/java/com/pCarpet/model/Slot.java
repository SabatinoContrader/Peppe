package com.pCarpet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data

@Entity
public class Slot {
	
	@Id
	@Column(name="id_slot")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	@NotNull
    private int number_carplace;
	
	@Column
	@NotNull
    private int number_carplace_free;
	
	@Column
    private double latitude;
	
	@Column
    private double longitude;
	
	@Column
    private String address;
	
	@Column
    private float price;
	
	@Column
    private String type;
	
	@ManyToOne
	@JoinColumn(name="username")
    private User user; 

}