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

public class UserDAO
{
    private final String QUERY_ALL = "select * from user";
    //private final String QUERY_SINGLE_USER = "select * from user where username = ?";

    private final String QUERY_INSERT_USER = "insert into user (username,password,type,name,surname,birthdate,birthplace,address,handicapped) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String QUERY_USER_TYPE = "select type from user where username = ? ";

    private Map<String,User> map_users;

    public UserDAO()
    {
        map_users = new HashMap<String, User>();
    }

    public List<User> getAllUserModels (boolean force) {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            while (resultSet.next()) {
                String    username     = resultSet.getString("username");
                String    password     = resultSet.getString("password");
                String    type         = resultSet.getString("type");
                String    name         = resultSet.getString("name");
                String    surname      = resultSet.getString("surname");
                LocalDate birthdate   = ((java.sql.Date)resultSet.getObject("birthdate")).toLocalDate(); //get Object for Localdate
                String    birthplace   = resultSet.getString("birthplace");
                String    address      = resultSet.getString("address");
                Boolean   handicapped  = resultSet.getBoolean("handicapped");

                if(this.map_users.containsKey(username) && !force)
                {
                    users.add( this.map_users.get(username) );
                }
                else
                {
                    User user = new User(username, password, type, name, surname, birthdate, birthplace, address, handicapped);
                    users.add(user);
                    this.map_users.put(username, user);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean insertUser (User user)
    {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER);
            statement.setString(1, user.getUsername() );
            statement.setString(2, user.getPassword() );
            statement.setString(3, user.getType() );
            statement.setString(4, user.getName() );
            statement.setString(5, user.getSurname() );
            statement.setObject(6, user.getBirthdate() ); //set object for Localdate
            statement.setString(7, user.getBirthplace() );
            statement.setString(8, user.getAddress() );
            statement.setBoolean(9, user.isHandicapped() );

            int returnedvalue = statement.executeUpdate();
            if( !this.map_users.containsKey( user.getUsername() ) && (returnedvalue != -1) ) this.map_users.put(user.getUsername(), user);
            return (returnedvalue != -1);
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }

    public String userType (String username){
        Connection connection = ConnectionSingleton.getInstance();
        String type = "";
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_USER_TYPE);
            statement.setString(1, username );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                type = resultSet.getString("type");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }
}
