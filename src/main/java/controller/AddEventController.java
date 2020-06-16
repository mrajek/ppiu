package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import service.AdminService;

import java.io.IOException;
import java.sql.SQLException;

public class AddEventController {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_termin;

    @FXML
    private TextArea ta_agenda;

    @FXML
    private Button bt_addEvent;

    @FXML
    private Button bt_back;

    @FXML
    void backAction(ActionEvent event) throws IOException {
        StartController.loginService.getAdminView();
        StartController.loginService.closeStage(bt_back);
    }

    @FXML
    void addEvent(ActionEvent event) throws SQLException, IOException {
        AdminPanelController.adminService.addEvent(this.tf_name.getText(), this.ta_agenda.getText(), this.tf_termin.getText());
        StartController.loginService.getAdminView();
        StartController.loginService.closeStage(bt_addEvent);
    }
    public void initialize(){
        AdminPanelController.adminService = new AdminService();
    }
}