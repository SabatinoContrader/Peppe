package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.servlets.ConnectionSingleton;
//import main.controller.GestoreEccezioni;
import com.virtualpairprogrammers.domain.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    private final String QUERY_PAYMENT = "select * from payment where id_stop = ?";

    public Payment getPayment(int id_stop) {
        Payment payment = null;
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_PAYMENT);
            statement.setInt(1, id_stop);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_payment = resultSet.getInt("id_payment");
                float quantity = resultSet.getFloat("quantity");
                String username = resultSet.getString("username");
                int id_stops = resultSet.getInt("id_stop");
                payment = new Payment(id_payment, quantity, username, id_stops);
            }

        } catch (SQLException e) {
            //GestoreEccezioni.getInstance().gestisciEccezione(e);
        }
        return payment;
    }
}
