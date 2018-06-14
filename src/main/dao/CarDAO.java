package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Car;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private final String QUERY_ADDCAR = "insert into car (id_car, license_plate, name, size, username) values (?,?,?,?,?)";
    private final String QUERY_REMOVECAR = "delete from car where id_car = ? ";

    private final String QUERY_ALL = "select * from car where username = ?";
    private final String QUERY_CAR = "select * from car where id_car = ?";

    public boolean addcar(Car car) {
        Connection connection = ConnectionSingleton.getInstance();

        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ADDCAR);
            statement.setInt(1, car.getId_car());
            statement.setString(2, car.getLicensePlate());
            statement.setString(3, car.getName());
            statement.setString(4, car.getSize());
            statement.setString(5, car.getUsername());

            int returnedvalue = statement.executeUpdate();
            return (returnedvalue != -1);
        } catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }

    public boolean removecar(int id_car) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_REMOVECAR);
            statement.setInt(1, id_car);

            int returnedvalue = statement.executeUpdate();
            return (returnedvalue == 1);
        } catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }


    public List<Car> getAllCarModel(String username) {

        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ALL);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_car = resultSet.getInt("id_car");
                String license_plate = resultSet.getString("license_plate");
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                String user = resultSet.getString("username");


                cars.add(new Car(id_car, license_plate, name, size, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;

    }


    public Car getCar(int id_car) {
        Car car = null;
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_CAR);
            statement.setInt(1, id_car);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int car_id = resultSet.getInt("id_car");
                String license_plate = resultSet.getString("license_plate");
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                String user = resultSet.getString("username");
                car = new Car(car_id, license_plate, name, size, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }


}