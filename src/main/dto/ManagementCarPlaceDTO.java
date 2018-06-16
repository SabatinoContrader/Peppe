package main.dto;

import main.model.Car;
import main.model.Carplace;
import main.model.Stop;

public class ManagementCarPlaceDTO
{
    private Carplace carplace;
    private Stop stop;
    private Car car;

    public ManagementCarPlaceDTO(Carplace carplace, Stop stop, Car car) {
        this.carplace = carplace;
        this.stop = stop;
        this.car = car;
    }

    public Carplace getCarplace() {
        return carplace;
    }

    public void setCarplace(Carplace carplace) {
        this.carplace = carplace;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}