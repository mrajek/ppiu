package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.LoginService;

import java.io.IOException;


public class StartController {

    @FXML
    private Button bt_StartLogIn;

    @FXML
    private Button bt_StartSingUp;

    public LoginService loginService;

    @FXML
    void StartLogInAction(ActionEvent event) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        loginStage.setTitle("SalvAction");
        loginStage.setScene(new Scene(root));
        loginStage.show();
        loginService.closeStage(bt_StartLogIn);
    }

    @FXML
    void StartSingUpAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/singUpView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        loginService.closeStage(bt_StartSingUp);
    }
    public void initialize() {
        loginService = new LoginService();

    }
}
