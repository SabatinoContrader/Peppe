package main.dto;

public class ManagementCarPlaceDTO
{
    private int id_carplace;
    private int id_slot;
    private boolean type;
    private boolean busy;
    private String license_plate;
    private String start;
    private String finish;

    public ManagementCarPlaceDTO(int id_carplace, int id_slot, boolean type, boolean busy, String license_plate, String start, String finish) {
        this.id_carplace = id_carplace;
        this.id_slot = id_slot;
        this.type = type;
        this.busy = busy;
        this.license_plate = license_plate;
        this.start = start;
        this.finish = finish;
    }

    public ManagementCarPlaceDTO(int id_slot, boolean type, boolean busy, String license_plate, String start, String finish) {
        this.id_slot = id_slot;
        this.type = type;
        this.busy = busy;
        this.license_plate = license_plate;
        this.start = start;
        this.finish = finish;
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