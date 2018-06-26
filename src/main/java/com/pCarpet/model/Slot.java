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
public class Slot {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_slot;
	
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