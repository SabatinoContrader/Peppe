package com.pCarpet.dto;

import com.pCarpet.model.Car;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;

public class ManagementExtensionStopDTO {
    private int id_stop;
    private String address;
    private String start;
    private String finish;
    private String name;

    public ManagementExtensionStopDTO(int id_stop, String address, String start, String finish, String name) {
        this.id_stop = id_stop;
        this.address = address;
        this.start = start;
        this.finish = finish;
        this.name = name;
    }

    public ManagementExtensionStopDTO(Stop stop, Slot slot, Car car) {
        this.id_stop = stop.getId_stop();
        this.address = slot.getAddress();
        this.start = stop.getStart();
        this.finish = stop.getFinish();
        this.name = car.getName();
    }

    public ManagementExtensionStopDTO() {

    }

    public int getId_stop() {
        return id_stop;
    }

    public void setId_stop(int id_stop) {
        this.id_stop = id_stop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

