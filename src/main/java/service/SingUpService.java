package service;

import controller.SingUpController;
import controller.StartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingUpService {
    public boolean singUp(String name, String lastname, String gender, String email, String login, String password) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("INSERT INTO user(name, lastname, gender, email, login, password)  VALUES(?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastname);
        preparedStatement.setString(3,gender);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,login);
        preparedStatement.setString(6,password);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Zarejestrowano poprawnie: ");
            return true;
        } else {
            System.out.println("Błąd rejestracji");
            return false;
        }
    }

    public void changeStage() throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/startView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
    }
}
