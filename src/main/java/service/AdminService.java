package service;

import controller.LoginController;
import controller.StartController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {
    public boolean remove(String login) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("DELETE FROM user WHERE login = ?");
            preparedStatement.setString(1, login);
            Integer i = preparedStatement.executeUpdate();
            System.out.println("Usunieto poprawnie");
            showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Usuniecie uzytkownika powiodlo sie");
            return true;
        }catch(SQLException e){
            System.out.println("Błąd");
            showAlert(Alert.AlertType.ERROR, "Blad", "Nie udalo sie usunac uzytkownika");
            return false;
        }
    }

    public  boolean resetPass(String login) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE user SET password = 1234 WHERE login = ?");
        preparedStatement.setString(1,login);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Zresetowano haslo");
            showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Zresetowano haslo");
            return true;
        } else {
            System.out.println("Błąd");
            showAlert(Alert.AlertType.ERROR, "Blad", "Nie udalo sie zresetowac hasla");
            return false;
        }
    }

    public boolean addEvent(String name, String agenda, String date) {
        try{
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("INSERT INTO event(name, agenda, termin)  VALUES(?, ?, ?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,agenda);
            preparedStatement.setString(3,date);
            Integer i = preparedStatement.executeUpdate();
            if(i == 1){
                System.out.println("Utworzono poprawnie: ");
                showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Utworzono wydarzenie");
                return true;
            } else {
                System.out.println("Błąd tworzenia");
                return false;
            }
        } catch (SQLException e){
            showAlert(Alert.AlertType.ERROR, "Blad", "Nie udalo sie utworzyc wydarzenia");
            return false;
        }

    }
    public boolean fillCB(ObservableList<String> observableList) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT name FROM event");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                observableList.add(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeEvent(String name) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("DELETE FROM event WHERE name = ?");
            preparedStatement.setString(1, name);
            Integer i = preparedStatement.executeUpdate();
            System.out.println("Usunieto poprawnie");
            showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Usunieto wydarzenie");
            return true;
        }catch(SQLException e){
            showAlert(Alert.AlertType.ERROR, "Blad", "Nie udalo sie usunac wydarzenia");
            return false;
        }
    }

    public boolean getValues(String name, TextArea ta, TextField tf) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ta.setText(resultSet.getString(3));
                tf.setText(resultSet.getString(4));
            }
            return true;
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public  boolean editEvent(String name, String agenda, String date) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE event SET agenda = ?, termin = ? WHERE name = ?");
        preparedStatement.setString(1,agenda);
        preparedStatement.setString(2,date);
        preparedStatement.setString(3,name);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Wprowadzono zmiany");
            showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Dokonano zmian");
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Blad", "Nie udalo się dokonac zmian");
            return false;
        }
    }

    public boolean rejectEnrollment(String event, String name){
        try {
            String id_event = null;
            String id_user = null;
            PreparedStatement eventId = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            eventId.setString(1, event);
            ResultSet eventRS = eventId.executeQuery();
            if(eventRS.next()) id_event = eventRS.getString(1);

            PreparedStatement user = StartController.connection.prepareStatement("SELECT * FROM user WHERE name = ?");
            user.setString(1, name);
            ResultSet userRS = user.executeQuery();
            if(userRS.next())  id_user = userRS.getString(1);

            PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE hash_event SET status = 'rejected' WHERE id_event = ? && id_user = ?");
            preparedStatement.setString(1, id_event);
            preparedStatement.setString(2, id_user);
            Integer i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("Wprowadzono zmiany");
                showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Odrzucono udział w wydarzeniu uzytkownika" + name);
                return true;
            } else {
                System.out.println("Błąd");
                return false;
            }
        }  catch (SQLException e){
            showAlert(Alert.AlertType.ERROR, "Bląd", "Nie udalo się odrzucic udzialu");
            return false;
        }

    }

    public boolean confirmEnrollment(String event, String name){
        try {
            String id_event = null;
            String id_user = null;
            PreparedStatement eventId = StartController.connection.prepareStatement("SELECT * FROM event WHERE name = ?");
            eventId.setString(1, event);
            ResultSet eventRS = eventId.executeQuery();
            if(eventRS.next()) id_event = eventRS.getString(1);
            System.out.println(id_event);

            PreparedStatement user = StartController.connection.prepareStatement("SELECT * FROM user WHERE name = ?");
            user.setString(1, name);
            ResultSet userRS = user.executeQuery();
            if(userRS.next())  id_user = userRS.getString(1);
            System.out.println(id_user);
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE hash_event SET status = 'confirmed' WHERE id_event = ? && id_user = ?");
            preparedStatement.setString(1, id_event);
            preparedStatement.setString(2, id_user);
            Integer i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("Wprowadzono zmiany");
                showAlert(Alert.AlertType.INFORMATION, "Potwierdzenie", "Przyjęto udział w wydarzeniu uzytkownika" + name);
                return true;
            } else {
                System.out.println("Błąd");
                return false;
            }
        }  catch (SQLException e){
            showAlert(Alert.AlertType.ERROR, "Bląd", "NIe udalo się zaakceptowac udzialu.");
            return false;
        }

    }

    public boolean getUsers(String event, ObservableList<String> observableList){
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT u.name FROM user as u INNER JOIN hash_event as he ON u.id_user = he.id_user INNER JOIN event as e ON he.id_event = e.id_event WHERE e.name = ? AND he.status = 'waiting'");
            preparedStatement.setString(1, event);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                observableList.add(resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();
            return true;
        }  catch (SQLException e){
            e.printStackTrace();
            return false;
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
