package com.pCarpet.dto;

import com.pCarpet.model.Car;
import com.pCarpet.model.Stop;

public class ManagementCarPlaceDTO {
    private int id_slot;
    private String license_plate;
    private String start;
    private String finish;

    public ManagementCarPlaceDTO(Stop stop, Car car) {
        this.id_slot = stop.getSlot().getId();
        this.license_plate = car.getLicense_plate();
        this.start = stop.getStart();
        this.finish = stop.getFinish();
    }



    public int getId_slot() {
        return id_slot;
    }

    public void setId_slot(int id_slot) {
        this.id_slot = id_slot;
    }


    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}