package main.dto;

import main.model.Car;
import main.model.Carplace;
import main.model.Stop;

public class ManagementCarPlaceDTO
{
    private int id_carplace;
    private int id_slot;
    private boolean type;
    private boolean busy;
    private String license_plate;
    private String start;
    private String finish;

    public ManagementCarPlaceDTO(Carplace carplace, Stop stop, Car car) {
        id_carplace = carplace.getId_carplace();
        id_slot = carplace.getId_slot();
        type = carplace.getType();
        busy = carplace.isBusy();
        license_plate = car.getLicensePlate();
        start = stop.getStart();
        finish = stop.getFinish();
    }

    public int getId_carplace() {
        return id_carplace;
    }

    public void setId_carplace(int id_carplace) {
        this.id_carplace = id_carplace;
    }

    public int getId_slot() {
        return id_slot;
    }

    public void setId_slot(int id_slot) {
        this.id_slot = id_slot;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
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