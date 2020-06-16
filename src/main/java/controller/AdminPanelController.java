package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import service.AdminService;


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

    @FXML
    private ComboBox<String> cb_events;

    @FXML
    private Button bt_utworzEvent;

    @FXML
    private Button bt_edytujEvent;

    @FXML
    private Button bt_usunEvent;

    @FXML
    private ComboBox<String> cb_event;

    @FXML
    private Button bt_confirm;

    @FXML
    private Button bt_reject;

    @FXML
    private ComboBox<String> cb_users;

    @FXML
    private Button bt_logout;


    public static AdminService adminService;

    private ObservableList<String> options = FXCollections.observableArrayList();
    private ObservableList<String> users = FXCollections.observableArrayList();


    @FXML
    void dodajAction(ActionEvent event) throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addUserView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        StartController.loginService.closeStage(bt_dodaj);
    }

    @FXML
    void resetAction(ActionEvent event) throws SQLException {
        adminService.resetPass(tf_loginReset.getText());
    }

    @FXML
    void usunAction(ActionEvent event) {
        adminService.remove(tf_loginUsun.getText());
    }

    @FXML
    void addEventAction(ActionEvent event) throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/addEventView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        StartController.loginService.closeStage(bt_utworzEvent);
    }

    @FXML
    void usunEvent(ActionEvent event) {
        adminService.removeEvent(this.cb_events.getSelectionModel().getSelectedItem());
        options.clear();
        cb_events.setItems(options);
        cb_event.setItems(options);
    }

    @FXML
    void editEvent(ActionEvent event) throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/editEventView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        StartController.loginService.closeStage(bt_edytujEvent);
    }
    @FXML
    void rejectAction(ActionEvent event) {
        adminService.rejectEnrollment(cb_event.getSelectionModel().getSelectedItem(), cb_users.getSelectionModel().getSelectedItem());
        users.clear();
        adminService.getUsers(cb_event.getSelectionModel().getSelectedItem(), users);
        cb_users.setItems(users);
    }

    @FXML
    void confirmAction(ActionEvent event) {
        adminService.confirmEnrollment(cb_event.getSelectionModel().getSelectedItem(), cb_users.getSelectionModel().getSelectedItem());
        users.clear();
        adminService.getUsers(cb_event.getSelectionModel().getSelectedItem(), users);
        cb_users.setItems(users);
    }

    @FXML
    void selectEvent(ActionEvent event) {
        adminService.getUsers(cb_event.getSelectionModel().getSelectedItem(), users);
        cb_users.setItems(users);
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        Stage registerStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        registerStage.setTitle("SalvAction");
        registerStage.setScene(new Scene(root));
        registerStage.show();
        StartController.loginService.closeStage(bt_logout);
    }

    public void initialize() {
        adminService = new AdminService();
        adminService.fillCB(options);
        cb_events.setItems(options);
        cb_event.setItems(options);
    }
}
