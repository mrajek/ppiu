package service;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public boolean logIn(String login, String password, Node node) throws SQLException, IOException {
        // Batch processing SQL
        PreparedStatement preparedStatement = LoginController.connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        // wykonanie zaptytania
        ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()) {
            System.out.println("Zalogowano poprawnie: " + resultSet.getString(7));
                //if(resultSet.getString(4) == "admin") {
                    getAdminView();
                    closeStage(node);
                    return true;
                /*} else {
                    getUserView();
                    closeStage(node);
                    return true;
                }*/
        } else {
            System.out.println("Błąd logowania");
            return false;
        }
    }

    public void getAdminView() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel.fxml"));
        stage.setTitle("PANEL ADMINISTRATORA");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void getUserView() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        stage.setTitle("PANEL USER");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void closeStage(Node node){
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
