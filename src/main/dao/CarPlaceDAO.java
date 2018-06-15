package main.dao;

import main.ConnectionSingleton;
import main.model.Carplace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarPlaceDAO {

    private final String QUERY_ALL_CARPLACE = "select * from carplace where id_slot = ?";

    public CarPlaceDAO(){

    }

    public List<Carplace> getAllCarPlace (int id_slot){
        List<Carplace> carplace = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ALL_CARPLACE);
            statement.setInt(1, id_slot);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_carplace = resultSet.getInt("id_carplace");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                boolean type = resultSet.getBoolean("type");
                boolean busy = resultSet.getBoolean("busy");
                int id_slots = resultSet.getInt("id_slot");
                carplace.add(new Carplace(id_carplace, latitude, longitude, type, busy, id_slots));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return carplace;
    }
}
