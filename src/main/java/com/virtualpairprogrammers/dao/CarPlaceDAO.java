package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.servlets.ConnectionSingleton;
import com.virtualpairprogrammers.domain.Carplace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarPlaceDAO {

    private final String QUERY_ALL_CARPLACE = "select * from carplace where id_slot = ?";
    private final String QUERY_CARPLACE = "select * from carplace where id_carplace = ?";

    public CarPlaceDAO() {

    }

    public List<Carplace> getAllCarPlace(int id_slot) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carplace;
    }

    public Carplace getCarplace(int id_carplace) {
        Carplace carplace = null;
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_CARPLACE);
            statement.setInt(1, id_carplace);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_carplaces = resultSet.getInt("id_carplace");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                boolean type = resultSet.getBoolean("type");
                boolean busy = resultSet.getBoolean("busy");
                int id_slots = resultSet.getInt("id_slot");
                carplace = new Carplace(id_carplaces, latitude, longitude, type, busy, id_slots);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carplace;
    }
}