package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class StartController {

    @FXML
    private Button bt_StartLogIn;

    @FXML
    private Button bt_StartSingUp;

    @FXML
    void StartLogInAction(ActionEvent event) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        loginStage.setTitle("SalvAction");
        loginStage.setScene(new Scene(root));
        loginStage.show();
        Stage primaryStage = (Stage) bt_StartLogIn.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void StartSingUpAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/singUpView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        Stage primaryStage = (Stage) bt_StartSingUp.getScene().getWindow();
        primaryStage.close();
    }

}
