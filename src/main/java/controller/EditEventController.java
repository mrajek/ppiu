package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class EditEventController {

    @FXML
    private Button bt_editEvent;

    @FXML
    private Button bt_back;

    @FXML
    private ComboBox<String> cb_event;

    @FXML
    private TextArea ta_agenda;

    @FXML
    private TextField tf_data;

    private ObservableList<String> options = FXCollections.observableArrayList();

    private void fillComboBox() throws SQLException {
        AdminPanelController.adminService.fillCB(options);
        cb_event.setItems(options);
    }

    @FXML
    void backAction(ActionEvent event) throws IOException {
        StartController.loginService.getAdminView();
        StartController.loginService.closeStage(bt_back);
    }

    @FXML
    void editAction(ActionEvent event) throws SQLException {
        AdminPanelController.adminService.editEvent(this.cb_event.getSelectionModel().getSelectedItem(), this.ta_agenda.getText(), this.tf_data.getText());
    }

    private void setValues() throws SQLException {
        if(cb_event.getSelectionModel().getSelectedItem() != "") {
            AdminPanelController.adminService.getValues(cb_event.getSelectionModel().getSelectedItem(), ta_agenda, tf_data);
        }
    }

    @FXML
    void getValuesAction(ActionEvent event) throws SQLException {
        setValues();
    }

    public void initialize() throws SQLException {
        fillComboBox();
    }
}