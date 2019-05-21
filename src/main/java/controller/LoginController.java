package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.LoginService;
import util.DBConnector;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button bt_login;

    @FXML
    private Button bt_singUp;


    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_password;

    private DBConnector dbConnector;
    public LoginService loginService;
    public static Connection connection;

    @FXML
    void loginAction(ActionEvent event) throws SQLException, IOException{
        this.loginService.logIn(this.tf_login.getText(), this.pf_password.getText(), this.tf_login);
    }

    @FXML
    void keyLoginAction(KeyEvent event) throws SQLException, IOException{
        if (event.getCode() == KeyCode.ENTER) {
            this.loginService.logIn(this.tf_login.getText(), this.pf_password.getText(), this.tf_login);
        }
    }
    @FXML
    void registerAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/singUpView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        loginService.closeStage(bt_singUp);
    }
    public void initialize() throws SQLException {
        dbConnector = new DBConnector();
        connection = dbConnector.initializeConnection();
        loginService = new LoginService();

    }
}
