package com.pCarpet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data

@Entity
public class Carplace {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carplace;
	
	@Column
    private double latitude;
	
	@Column
    private double longitude;
	
	@Column
    private boolean type;
	
	@Column
    private boolean busy;
	
	@ManyToOne
	@JoinColumn(name="id_slot")
    private Slot slot;
}