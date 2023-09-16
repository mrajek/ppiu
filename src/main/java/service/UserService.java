package service;


import controller.StartController;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public boolean getValues(String eventCB, Label eventName, TextArea ta, Label data, Label eventId) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            preparedStatement.setString(1, eventCB);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                eventId.setText(resultSet.getString(1));
                eventName.setText(resultSet.getString(2));
                ta.setText(resultSet.getString(3));
                data.setText(resultSet.getString(4));
            }
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean enroll(String user, String event, String participation, String catering){
        try {
            PreparedStatement preparedStatement1 = StartController.connection.prepareStatement("INSERT INTO hash_event(id_user, id_event, typ_participation, catering)  VALUES(?, ?, ?, ?)");
            preparedStatement1.setString(1,user);
            preparedStatement1.setString(2,event);
            preparedStatement1.setString(3,participation);
            preparedStatement1.setString(4,catering);
            Integer i = preparedStatement1.executeUpdate();
            if(i == 1){
                System.out.println("Utworzono poprawnie: ");
                showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Dokonano zapisu na wydarzenie.");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Bląd", "Nie udało się zapisac na wydarzenie, sprawdz formularz");
            return  false;
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
