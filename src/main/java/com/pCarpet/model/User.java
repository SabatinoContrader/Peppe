package com.pCarpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.*;

//@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User {
	
	@Id
	@Column
    private String username;  
	
	@Column
	@NotNull
    private String password;
	
	@Column
	@NotNull
    private String type;
	
	@Column
    private String name;
	
	@Column
    private String surname;
	
	@Column
    private String birthdate;
	
	@Column
    private String birthplace;
	
	@Column
    private String address;
	
	@Column
    private Boolean handicapped;

}
