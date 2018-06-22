package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.servlets.ConnectionSingleton;
import com.virtualpairprogrammers.domain.Slot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {
    private final String QUERY_ALL = "select * from slot";
    private final String QUERY_SLOT = "select * from slot where id_slot = ?";
    private final String QUERY_NEAR_SLOT = "select * from slot where (ABS(latitude - ?)) < 0.01 AND (ABS(longitude - ?)) < 0.01";

    public SlotDAO() {

    }

    public List<Slot> getAllSlot() {
        List<Slot> slots = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            while (resultSet.next()) {
                int id_slot = resultSet.getInt("id_slot");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                String address = resultSet.getString("address");
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                String username = resultSet.getString("username");
                slots.add(new Slot(id_slot, latitude, longitude, address, price, type, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;

    }

    public Slot getSlot(int id_slot) {
        Slot slot = null;
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_SLOT);
            statement.setInt(1, id_slot);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_slots = resultSet.getInt("id_slot");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                String address = resultSet.getString("address");
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                String username = resultSet.getString("username");
                slot = new Slot(id_slots, latitude, longitude, address, price, type, username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slot;
    }
    
    public List<Slot> getNearSlot(double lat, double lng){
    	List<Slot> slots = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
        	PreparedStatement statement = connection.prepareStatement(QUERY_NEAR_SLOT);
            statement.setDouble(1, lat);
            statement.setDouble(2, lng);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_slot = resultSet.getInt("id_slot");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                String address = resultSet.getString("address");
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                String username = resultSet.getString("username");
                slots.add(new Slot(id_slot, latitude, longitude, address, price, type, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }
    
}
