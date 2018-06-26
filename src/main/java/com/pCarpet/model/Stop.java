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
import lombok.*;

@Data

@Entity
public class Stop {
	
	@Id	
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_stop;
	
	@Column
    private String start;
	
	@Column
    private String finish;
	
	
	@ManyToOne
    @JoinColumn(name="id_car")
	private Car car;
	
	@ManyToOne
    @JoinColumn(name="id_carplace")
	private Carplace carplace;
	
}   	
	
  