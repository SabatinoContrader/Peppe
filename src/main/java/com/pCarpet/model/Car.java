package com.pCarpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data

@Entity
public class Car {
	
	@Id
	@Column(name = "id_car")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	@NotNull
    private String license_plate;
	
	@Column
	@NotNull
    private String name;
	
	@Column
	@NotNull
    private String size;
	
	@ManyToOne
	@JoinColumn(name = "username")
    private User user; 

}

