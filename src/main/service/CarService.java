package main.service;

import main.dao.CarDAO;
import main.model.Car;
import main.model.Gomma;

import java.util.List;

public class CarService
{
    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public boolean addcar(Car car)
    {
        return this.carDAO.addcar(car);
    }

    public boolean removecar(int id_car){
        return this.carDAO.removecar(id_car);
    }



    public List<Car> getAllCarModel(String username,boolean force) {
        return this.carDAO.getAllCarModel(username,force);
    }

    public Car getCar(int id_car,boolean force){
        return this.carDAO.getCar(id_car,force);
    }
}
