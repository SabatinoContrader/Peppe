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

//@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
	
	@Column
	@NotNull
    private Boolean expired;	
	
	@ManyToOne
    @JoinColumn(name="id_car")
	private Car car;
	
	@ManyToOne
    @JoinColumn(name="id_slot")
	private Slot slot;
	
}   	
	
  