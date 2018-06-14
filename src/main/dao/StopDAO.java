package main.dao;

import main.ConnectionSingleton;
import main.model.Stop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StopDAO {

    private final String QUERY_STOP = "select * from stop where id_carplace = ?";

    public StopDAO() {

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
