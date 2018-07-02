package com.pCarpet.dto;

import java.util.List;

import com.pCarpet.model.Car;
import com.pCarpet.model.Payment;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;

public class ManagementCarPlaceDTO {
    //private int id_slot;
    //private String license_plate;

    private Slot slot;
    private List<Stop> stop_list;
    private List<Payment> payment_list;

    public ManagementCarPlaceDTO(Slot slot,List<Payment> payment, List<Stop> stop) {
        //this.id_slot = stop.getSlot().getId();
        //this.license_plate = car.getLicense_plate();
        
        this.slot = slot;
        this.payment_list = payment;
        this.stop_list = stop;

    }

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public List<Stop> getStop_list() {
		return stop_list;
	}

	public void setStop_list(List<Stop> stop_list) {
		this.stop_list = stop_list;
	}

	public List<Payment> getPayment_list() {
		return payment_list;
	}

	public void setPayment_list(List<Payment> payment_list) {
		this.payment_list = payment_list;
	}
    
    



//    public int getId_slot() {
//        return id_slot;
//    }
//
//    public void setId_slot(int id_slot) {
//        this.id_slot = id_slot;
//    }
//

//    public String getLicense_plate() {
//        return license_plate;
//    }
//
//    public void setLicense_plate(String license_plate) {
//        this.license_plate = license_plate;
//    }
//
//    public String getStart() {
//        return start;
//    }
//
//    public void setStart(String start) {
//        this.start = start;
//    }
//
//    public String getFinish() {
//        return finish;
//    }
//
//    public void setFinish(String finish) {
//        this.finish = finish;
//    }
}