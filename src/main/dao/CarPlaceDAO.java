package main.dao;

import main.ConnectionSingleton;
import main.model.Carplace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarPlaceDAO {

    private final String QUERY_ALL_CARPLACE = "select * from carplace";

    public CarPlaceDAO(){

    }

    public List<Carplace> getAllCarPlace (){
        List<Carplace> carplace = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_CARPLACE);
            while (resultSet.next()) {
                int id_carplace = resultSet.getInt("id_carplace");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                boolean type = resultSet.getBoolean("type");
                int id_slot = resultSet.getInt("id_slot");
                carplace.add(new Carplace(id_carplace, latitude, longitude, type, id_slot));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return carplace;
    }
}
