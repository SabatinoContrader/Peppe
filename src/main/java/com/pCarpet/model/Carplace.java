package com.pCarpet.model;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

//@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Carplace {

	@Id
	@Column(name = "id_carplace")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
    private double latitude;
	
	@Column
    private double longitude;
	
	@Column
    private Boolean type;
	
	@ManyToOne
	@JoinColumn(name="id_slot")
    private Slot slot;
	
	@Column
    private Boolean busy;
}