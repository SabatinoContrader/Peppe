package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
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
    private final String QUERY_SHOW_STOP = "Select s.id_stop, sl.address, s.start, s.finish, ca.name from stop s, slot sl, carplace c, car ca where s.id_car=ca.id_car and ca.username=? and c.id_slot=sl.id_slot and c.id_carplace=s.id_carplace";
    private final String QUERY_EXTENSION_STOP = "UPDATE stop SET finish = finish + INTERVAL ? MINUTE WHERE id_stop = ?";


    public StopDAO() {

    }


    public List<ManagementExtensionStopDTO> getAllExtensionStop(String username) {
        List<ManagementExtensionStopDTO> stops = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_SHOW_STOP);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_stop = resultSet.getInt("id_stop");
                String address = resultSet.getString("address");
                String start = resultSet.getString("start");
                String finish = resultSet.getString("finish");
                String name = resultSet.getString("name");

                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(id_stop, address, start, finish, name);
                stops.add(managementExtensionStopDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;

    }

    public boolean extensionStop(int minute, int id_slot){
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_EXTENSION_STOP);
            statement.setInt(1, minute );
            statement.setInt(2, id_slot );


            statement.executeUpdate();

        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
        return true;
    }

    public Stop getStop(int id_carplace) {
        Stop stop = null;
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_STOP);
            statement.setInt(1, id_carplace);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_stop = resultSet.getInt("id_stop");
                String start = resultSet.getString("start");
                String finish = resultSet.getString("finish");
                int id_car = resultSet.getInt("id_car");
                stop = new Stop(id_stop, start, finish, id_car, id_carplace);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stop;
    }
}
