package service;

import controller.LoginController;
import controller.StartController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
            return true;
        }catch(SQLException e){
            System.out.println("Błąd");
            return false;
        }
    }

    public  boolean resetPass(String login) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE user SET password = 1234 WHERE login = ?");
        preparedStatement.setString(1,login);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Zresetowano haslo");
            return true;
        } else {
            System.out.println("Błąd");
            return false;
        }
    }

    public boolean addEvent(String name, String agenda, String date) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("INSERT INTO event(name, agenda, termin)  VALUES(?, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,agenda);
        preparedStatement.setString(3,date);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Utworzono poprawnie: ");
            return true;
        } else {
            System.out.println("Błąd tworzenia");
            return false;
        }
    }
    public boolean fillCB(ObservableList<String> observableList) throws SQLException {
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
            System.out.println("Błąd wypelniania");
            return false;
        }
    }

    public boolean removeEvent(String name) {
        try {
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("DELETE FROM event WHERE name = ?");
            preparedStatement.setString(1, name);
            Integer i = preparedStatement.executeUpdate();
            System.out.println("Usunieto poprawnie");
            return true;
        }catch(SQLException e){
            System.out.println("Błąd: " + e);
            return false;
        }
    }

    public boolean getValues(String name, TextArea ta, TextField tf) throws SQLException {
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
            System.out.println("Błąd wypelniania");
            return false;
        }
    }

    public  boolean editEvent(String name, String agenda, String date) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("UPDATE event SET agenda = ?, termin = ? WHERE name = ?");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,agenda);
        preparedStatement.setString(3,date);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Wprowadzono zmiany");
            return true;
        } else {
            System.out.println("Błąd");
            return false;
        }
    }
}
