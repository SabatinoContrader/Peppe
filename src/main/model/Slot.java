package main.model;

public class Slot {
    private int id_slot;
    private double latitude;
    private double longitude;
    private String address;
    private float price;
    private String type;
    private String username; // foreign key

    public Slot(int id_slot, double latitude, double longitude, String address, float price, String type, String username) {
        this.id_slot = id_slot;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.price = price;
        this.type = type;
        this.username = username;
    }

    public int getId_slot() {
        return id_slot;
    }

    public void setId_slot(int id_slot) {
        this.id_slot = id_slot;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Id Slot: " + id_slot + "\nLatitude: " +latitude + "\nLongitude: "+longitude+"\nAddress: " +address +"\nPrice: "+price+"\nType: "+type+"\nUsername: "+username+"\n";

    }
}
