package service;

import controller.StartController;
import controller.UserPanelController;
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
    public boolean logIn(String login, String password, Node node) {
        try{
            PreparedStatement preparedStatement = StartController.connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Zalogowano poprawnie: " + resultSet.getString(7));
                if(resultSet.getString(4).equals("admin")) {
                    getAdminView();
                    closeStage(node);
                    return true;
                } else if (resultSet.getString(4).equals("user")){
                    getUserView(resultSet.getString(2), resultSet.getString(1));
                    closeStage(node);
                    return true;
                } else {
                    System.out.println("Brak uprawnień");
                    return false;
                }
            } else {
                System.out.println("Błąd logowania");
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return  false;
        }
    }

    public void getAdminView() {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel.fxml"));
            stage.setTitle("PANEL ADMINISTRATORA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void getUserView(String name, String id) {
       try{

           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userPanel.fxml"));
           Parent root =  loader.load();
           UserPanelController userPanelController = loader.getController();
           userPanelController.setText(name, id);
           Stage stage = new Stage();
           stage.setTitle("PANEL USER");
           stage.setScene(new Scene(root));
           stage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
    public void closeStage(Node node){
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
