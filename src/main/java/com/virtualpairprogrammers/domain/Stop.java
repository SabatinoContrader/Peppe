package com.virtualpairprogrammers.domain;

public class Stop {
    private int id_stop;
    private String start;
    private String finish;
    private int id_car;       // foreign key
    private int id_carplace;  // foreign key

    public Stop(int id_stop, String start, String finish, int id_car, int id_carplace) {
        this.id_stop = id_stop;
        this.start = start;
        this.finish = finish;
        this.id_car = id_car;
        this.id_carplace = id_carplace;
    }

    public Stop(String start, String finish, int id_car, int id_carplace) {
        this.start = start;
        this.finish = finish;
        this.id_car = id_car;
        this.id_carplace = id_carplace;
    }

    public int getId_stop() {
        return id_stop;
    }

    public void setId_stop(int id_stop) {
        this.id_stop = id_stop;
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

    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    public int getId_carplace() {
        return id_carplace;
    }

    public void setId_carplace(int id_carplace) {
        this.id_carplace = id_carplace;
    }

    @Override
    public String toString() {
        return "Id Stop: " + id_stop + "\nStart: " +start + "\nFinish: "+finish+"\nId Car: " +id_car +"\nId Carplace: "+id_carplace+"\n";

    }
}

