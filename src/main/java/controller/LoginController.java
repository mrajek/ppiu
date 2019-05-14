package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button bt_logIn;

    @FXML
    private Button bt_singUp;


    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_password;

    @FXML
    void logInAction(ActionEvent event) {
        String login;
        String password;
        try {
            login = tf_login.getText();
            password = pf_password.getText();
            if (login.equals("") || password.equals("")) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e){
            System.out.println("blad");
        }
    }

    @FXML
    void registerAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/singUpView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        Stage primaryStage = (Stage) bt_singUp.getScene().getWindow();
        primaryStage.close();
    }
}
