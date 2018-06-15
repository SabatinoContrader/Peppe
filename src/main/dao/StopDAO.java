package main.dao;

import main.ConnectionSingleton;
import main.dto.ManagementCarPlaceDTO;
import main.dto.ManagementExtensionStopDTO;
import main.model.Stop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopDAO {

    private final String QUERY_STOP = "select * from stop where id_carplace = ?";
    private final String QUERY_ALL_STOP = "select s.id_carplace, s.start, s.finish, ca.type, ca.busy, c.license_plate from stop s, car c, carplace ca where s.id_car = c.id_car and ca.id_slot = ? and ca.id_carplace = s.id_carplace";
    private final String QUERY_EXTENSION_STOP = "Select sl.address, s.start, s.finish, ca.name from stop s, slot sl, carplace c, car ca where s.id_car=ca.id_car and ca.username=? and c.id_slot=sl.id_slot and c.id_carplace=s.id_carplace";

    public StopDAO() {

    }

    public List<ManagementCarPlaceDTO> getAllStop(int id_slot) {
        List<ManagementCarPlaceDTO> stops = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ALL_STOP);
            statement.setInt(1, id_slot);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id_carplace = resultSet.getInt("id_carplace");

                boolean type = resultSet.getBoolean("type");
                boolean busy = resultSet.getBoolean("busy");
                String license_plate = resultSet.getString("license_plate");
                String start = resultSet.getString("start");
                String finish = resultSet.getString("finish");

                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(id_carplace, id_slot, type, busy, license_plate, start, finish);
                stops.add(managementCarPlaceDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;


    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(String username) {
        List<ManagementExtensionStopDTO> stops = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_EXTENSION_STOP);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String address = resultSet.getString("address");
                String start = resultSet.getString("start");
                String finish = resultSet.getString("finish");
                String name = resultSet.getString("name");

                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(address, start, finish, name);
                stops.add(managementExtensionStopDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;

    }
}
