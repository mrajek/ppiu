package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController {

    @FXML
    private Button bt_login;

    @FXML
    private Button bt_singUp;


    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_password;

    int i =0;
    @FXML
    void loginAction(ActionEvent event){
        if(i <3)
            StartController.loginService.logIn(this.tf_login.getText(), this.pf_password.getText(), this.tf_login);
        i++;
        if(i >=3){
            getAlert();
        }
    }

    @FXML
    void keyLoginAction(KeyEvent event) {
        if(i < 3) {
            if (event.getCode() == KeyCode.ENTER) {
                StartController.loginService.logIn(this.tf_login.getText(), this.pf_password.getText(), this.tf_login);
            }
        }
        i++;
        if(i >=3){
            getAlert();
        }
    }
    @FXML
    void registerAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/singUpView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        StartController.loginService.closeStage(bt_singUp);
    }
    public void getAlert(){
        bt_login.setDisable(true);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Dłąd logowania");
        a.setHeaderText("Trzy błedne próby");
        a.setTitle("Spróboj ponownie pozniej!");
        a.showAndWait();
    }
}
