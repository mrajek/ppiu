package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import service.AdminService;
import service.LoginService;
import service.SingUpService;
import util.DBConnector;

import java.io.IOException;
import java.sql.SQLException;

public class AdminPanelController {

    @FXML
    private Button bt_dodaj;

    @FXML
    private Button bt_usun;

    @FXML
    private Button bt_reset;

    @FXML
    private TextField tf_loginUsun;

    @FXML
    private TextField tf_loginReset;

    public LoginService loginService;
    public AdminService adminService;


    @FXML
    void dodajAction(ActionEvent event) throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addUserView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        loginService.closeStage(bt_dodaj);
    }

    @FXML
    void resetAction(ActionEvent event) throws SQLException {
        this.adminService.resetPass(tf_loginReset.getText());
    }

    @FXML
    void usunAction(ActionEvent event) throws SQLException {
        this.adminService.remove(tf_loginUsun.getText());
    }
    public void initialize() {
        loginService = new LoginService();
        adminService = new AdminService();

    }
}
