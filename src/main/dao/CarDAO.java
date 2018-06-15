package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Car;
import main.model.Report;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarDAO {

    private final String QUERY_ADDCAR = "insert into car (license_plate, name, size, username) values (?,?,?,?)";
    private final String QUERY_REMOVECAR = "delete from car where id_car = ? ";

    private final String QUERY_ALL = "select * from car where username = ?";
    private final String QUERY_CAR = "select * from car where id_car = ?";

    private Map<Integer,Car> map_cars;

    public CarDAO()
    {
        map_cars = new HashMap<Integer,Car>();
    }

    //public Map<Integer, Car> getMap_cars() {
    //    return map_cars;
    //}

    public boolean addcar(Car car) {
        Connection connection = ConnectionSingleton.getInstance();

        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ADDCAR);
            statement.setString(1, car.getLicensePlate());
            statement.setString(2, car.getName());
            statement.setString(3, car.getSize());
            statement.setString(4, car.getUsername());

            int returnedvalue = statement.executeUpdate();
            if( !this.map_cars.containsKey( car.getId_car() ) && (returnedvalue != -1) ) this.map_cars.put(car.getId_car(), car);
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
            if( this.map_cars.containsKey( id_car ) && (returnedvalue == 1) ) this.map_cars.remove( id_car );
            return (returnedvalue == 1);
        } catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }


    public List<Car> getAllCarModel(String username, boolean force) {

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

                if(this.map_cars.containsKey(id_car) && !force)
                {
                    cars.add( this.map_cars.get(id_car) );
                }
                else
                {
                    Car car = new Car(id_car, license_plate, name, size, user);
                    cars.add(car);
                    this.map_cars.put(id_car, car);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;

    }


    public Car getCar(int id_car, boolean force) {
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
                //car = new Car(car_id, license_plate, name, size, user);

                if(this.map_cars.containsKey(id_car) && !force)
                {
                    car = this.map_cars.get(id_car);
                }
                else
                {
                    car = new Car(id_car, license_plate, name, size, user);
                    this.map_cars.put(id_car, car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }


}