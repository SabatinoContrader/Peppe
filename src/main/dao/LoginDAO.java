package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LoginDAO {

    private static User loggedUser;

    private final String QUERY_LOGIN = "select * from user where username = ? and password = ?";
    private final String QUERY_SIGNUP = "insert into user (username,password,type,birthdate,homeaddress) values (?, ?, ?, ?, ?)";

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean login (String username, String password ) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String   type         = resultSet.getString("type");
                if(type.equalsIgnoreCase("driver")) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    LocalDate birthdate = ((java.sql.Date) resultSet.getObject("birthdate")).toLocalDate(); //get Object for Localdate
                    String birthplace = resultSet.getString("birthplace");
                    String address = resultSet.getString("address");
                    Boolean handicapped = resultSet.getBoolean("handicapped");
                    loggedUser = new User(user, pass, type, name, surname, birthdate, birthplace, address, handicapped );
                }
                else
                    loggedUser = new User(user, pass, type);

            }
            return loggedUser != null;
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }

    public boolean signup (String username, String password, String type, String birthdate, String homeaddress ) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_SIGNUP);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, type);
            statement.setString(4, birthdate);
            statement.setString(5, homeaddress);

            int returnedvalue = statement.executeUpdate();
            return (returnedvalue != -1);
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }

    public void getUserModel (String user) {

        Connection connection = ConnectionSingleton.getInstance();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(QUERY_LOGIN);
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String   username     = resultSet.getString("username");
                String   password     = resultSet.getString("password");
                String   type         = resultSet.getString("type");
                String   name         = resultSet.getString("name");
                String   surname      = resultSet.getString("surname");
                LocalDate birthdate   = ((java.sql.Date)resultSet.getObject("birthdate")).toLocalDate(); //get Object for Localdate
                String   birthplace   = resultSet.getString("birthplace");
                String   address      = resultSet.getString("address");
                Boolean  handicapped  = resultSet.getBoolean("handicapped");
                loggedUser = new User(username, password, type, name, surname, birthdate, birthplace, address, handicapped );
        }
        }catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
        }

    }

    public void destroyUser(){
        loggedUser = null;
    }
}
