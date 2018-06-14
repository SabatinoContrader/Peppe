package main.model;

public class Car {
    private int id_car;
    private String license_plate;
    private String name;
    private String size;
    private String username; // foreign key


    public Car(int id_car, String license_plate, String name, String size, String username) {
        this.id_car = id_car;
        this.license_plate = license_plate;
        this.name = name;
        this.size = size;
        this.username = username;
    }

    public Car(String license_plate, String name, String size, String username) {
        this.license_plate = license_plate;
        this.name = name;
        this.size = size;
        this.username = username;
    }

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public String getLicensePlate() {
        return license_plate;
    }

    public void setLicensePlate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Car: " + id_car + "\nLicense Plate: " +license_plate + "\nName: "+name+"\nSize: " + size + "\nUsername: " + username + "\n";

    }
}


