package main.service;

import main.dao.StopDAO;
import main.model.Stop;

public class StopService {
    private StopDAO stopDAO;

    public  StopService() {
        this.stopDAO = new StopDAO();
    }

    public Stop getStop(int id_carplace) {
        return this.stopDAO.getStop(id_carplace);
    }
}
