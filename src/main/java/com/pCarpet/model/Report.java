package com.pCarpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Data

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
public class Report {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_report;
	
	@Column
    private int type;
	
	@Column
    private String description;
	
	@Column
    private String time;
	
	@ManyToOne
	@JoinColumn(name = "username")
    private User user; 	

}
