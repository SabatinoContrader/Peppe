package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.domain.Carplace;
import com.virtualpairprogrammers.dao.CarPlaceDAO;
import java.util.List;

public class CarPlaceService {

    private CarPlaceDAO carPlaceDAO;

    public CarPlaceService() {
        this.carPlaceDAO = new CarPlaceDAO();
    }

    public List<Carplace> getAllCarPlace(int id_slot){
        return this.carPlaceDAO.getAllCarPlace(id_slot);
    }

    public Carplace getCarplace(int id_carplace) {
        return this.carPlaceDAO.getCarplace(id_carplace);
    }
}
