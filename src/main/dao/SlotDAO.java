package main.dao;

import main.ConnectionSingleton;
import main.model.Slot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {
    private final String QUERY_ALL = "select * from slot";

    public SlotDAO() {

    }

    public List<Slot> getAllSlot () {
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;

    }
}
