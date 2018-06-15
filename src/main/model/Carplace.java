package main.model;

public class Carplace {

    private int id_carplace;
    private double latitude;
    private double longitude;
    private boolean type;
    private boolean busy;
    private int id_slot;

    public Carplace(int id_carplace, double latitude, double longitude, boolean type, boolean busy, int id_slot) {
        this.id_carplace = id_carplace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type= type;
        this.busy = busy;
        this.id_slot = id_slot;
    }

    public Carplace(double latitude, double longitude, boolean type, boolean busy, int id_slot) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type= type;
        this.busy = busy;
        this.id_slot = id_slot;
    }

    public int getId_carplace() {
        return id_carplace;
    }

    public void setId_carplace(int id_carplace){
        this.id_carplace = id_carplace;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean getType(){
        return type;
    }

    public void setType(boolean type){
        this.type = type;
    }

    public int getId_slot(){
        return id_slot;
    }

    public void setId_slot(int id_slot){
        this.id_slot = id_slot;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public String toString() {
        return "Carplace: " + id_carplace + "\nlatitude: " + latitude + "\nlongitude: " + longitude + "\ntype: " + type + "\nid_slot: " + id_slot + "\n";
}
}
