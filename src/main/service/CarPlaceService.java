package main.service;

import main.model.Carplace;
import main.dao.CarPlaceDAO;
import java.util.List;

public class CarPlaceService {

    private CarPlaceDAO carPlaceDAO;

    public CarPlaceService() {
        this.carPlaceDAO = new CarPlaceDAO();
    }

    public List<Carplace> getAllCarPlace(){
        return this.carPlaceDAO.getAllCarPlace();
    }
}
