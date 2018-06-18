package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private static User loggedUser;
    private Map<String, User> map_users;

    private final String QUERY_ALL = "select * from user";
    private final String QUERY_LOGIN = "select * from user where username = ? and password = ?";
    private final String QUERY_INSERT_USER = "insert into user (username,password,type,name,surname,birthdate,birthplace,address,handicapped) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UserDAO() {
        map_users = new HashMap<String, User>();
    }

    public boolean login(String username, String password) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String type = resultSet.getString("type");
                if (type.equalsIgnoreCase("driver")) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    LocalDate birthdate = ((java.sql.Date) resultSet.getObject("birthdate")).toLocalDate(); //get Object for Localdate
                    String birthplace = resultSet.getString("birthplace");
                    String address = resultSet.getString("address");
                    Boolean handicapped = resultSet.getBoolean("handicapped");
                    loggedUser = new User(user, pass, type, name, surname, birthdate, birthplace, address, handicapped);
                } else
                    loggedUser = new User(user, pass, type);

            }
            return loggedUser != null;
        } catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }


    public void destroyUser() {
        loggedUser = null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public List<User> getAllUserModels(boolean force) {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                LocalDate birthdate = ((java.sql.Date) resultSet.getObject("birthdate")).toLocalDate(); //get Object for Localdate
                String birthplace = resultSet.getString("birthplace");
                String address = resultSet.getString("address");
                Boolean handicapped = resultSet.getBoolean("handicapped");

                if (this.map_users.containsKey(username) && !force) {
                    users.add(this.map_users.get(username));
                } else {
                    User user = new User(username, password, type, name, surname, birthdate, birthplace, address, handicapped);
                    users.add(user);
                    this.map_users.put(username, user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean insertUser(User user) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getType());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setObject(6, user.getBirthdate()); //set object for Localdate
            statement.setString(7, user.getBirthplace());
            statement.setString(8, user.getAddress());
            statement.setBoolean(9, user.isHandicapped());

            int returnedvalue = statement.executeUpdate();
            if (!this.map_users.containsKey(user.getUsername()) && (returnedvalue != -1))
                this.map_users.put(user.getUsername(), user);
            return (returnedvalue != -1);
        } catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e, user.getUsername());
            return false;
        }
    }
}
