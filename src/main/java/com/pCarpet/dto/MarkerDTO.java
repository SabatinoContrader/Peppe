package com.pCarpet.dto;

import java.util.List;

import com.pCarpet.model.Carplace;
import com.pCarpet.model.Slot;

public class MarkerDTO 
{
	private Slot slot;
	private List<Carplace> carplace;
	
	public MarkerDTO(Slot slot, List<Carplace> carplace) {
		this.carplace = carplace;
		this.slot = slot;
	}

	public List<Carplace> getCarplace() {
		return carplace;
	}

	public void setCarplace(List<Carplace> carplace) {
		this.carplace = carplace;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
}
