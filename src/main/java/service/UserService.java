package service;


import controller.StartController;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean getValues(String name, Label event, TextArea ta, Label data, Label eventId) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                eventId.setText(resultSet.getString(1));
                event.setText(resultSet.getString(2));
                ta.setText(resultSet.getString(3));
                data.setText(resultSet.getString(4));
            }
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean enroll(String id_user, String id_event, String participation, String catering){
        try {
            PreparedStatement eventId = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            eventId.setString(1, id_event);
            ResultSet eventRS = eventId.executeQuery();

            PreparedStatement preparedStatement = StartController.connection.prepareStatement("INSERT INTO hash_event(id_user, id_event, typ_participation, catering)  VALUES(?, 1, ?, ?)");
            preparedStatement.setString(1,id_user);
            //preparedStatement.setString(2,eventRS.getString(1));
            preparedStatement.setString(2,participation);
            preparedStatement.setString(3,catering);
            Integer i = preparedStatement.executeUpdate();
            if(i == 1){
                System.out.println("Utworzono poprawnie: ");
                return true;
            } else {
                System.out.println("Błąd tworzenia");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }
}
