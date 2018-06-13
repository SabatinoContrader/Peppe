package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAO {

    private final String QUERY_LOGIN = "select * from users where username = ? and password = ?";
    private final String QUERY_SIGNUP = "insert into users (username,password,type,birthdate,homeaddress) values (?, ?, ?, ?, ?)";

    public boolean login (String username, String password ) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.executeQuery().next();
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
}
