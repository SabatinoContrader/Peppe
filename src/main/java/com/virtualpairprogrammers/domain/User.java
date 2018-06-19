package com.virtualpairprogrammers.domain;

import java.time.LocalDate;

public class User {
    private String username;  //primary key
    private String password;
    private String type;
    private String name;
    private String surname;
    private String birthdate;
    private String birthplace;
    private String address;
    private boolean handicapped;

    public User(String username, String password, String type, String name, String surname, String birthdate, String birthplace, String address, boolean handicapped) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.address = address;
        this.handicapped = handicapped;
    }

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHandicapped() {
        return handicapped;
    }

    public void setHandicapped(boolean handicapped) {
        this.handicapped = handicapped;
    }

    @Override
    public String toString() {
        return "username: " + username + "\npassword: " + password + "\ntype: " + type + "\nname: " + name + "\nsurname: " + surname + "\nbirthdate: " + birthdate + "\nbirthplace: " + birthplace + "\naddress: " + address + "\nhandicapped: " + handicapped;
    }

}
