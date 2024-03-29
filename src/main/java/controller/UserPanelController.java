package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.AdminService;
import service.UserService;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPanelController implements Initializable {
    @FXML
    private Label user_name;

    @FXML
    private Label user_id;

    @FXML
    private Label lb_eventId;

    @FXML
    private Button bt_enroll;

    @FXML
    private ComboBox<String> cb_event;

    @FXML
    private Label lb_nazwa;

    @FXML
    private Label lb_data;

    @FXML
    private TextArea ta_agenda;

    @FXML
    private ComboBox<String> cb_uczestnictwo;

    @FXML
    private ComboBox<String> cb_wyzywienie;

    @FXML
    private Button bt_logout;

    private static UserService userService;
    private  static  AdminService adminService;

    private ObservableList<String> participation = FXCollections.observableArrayList("Słuchacz", "Autor", "Sponsor", "Organizator");
    private ObservableList<String> catering = FXCollections.observableArrayList("Bez preferencji", "Wegetarianskie", "Bez glutenu");
    private ObservableList<String> options = FXCollections.observableArrayList();


    @FXML
    void enrollAction(ActionEvent event) {
        userService.enroll(user_id.getText(), lb_eventId.getText(), cb_uczestnictwo.getSelectionModel().getSelectedItem(), cb_wyzywienie.getSelectionModel().getSelectedItem());
    }

    @FXML
    void eventAction(ActionEvent event) {
        if(cb_event.getSelectionModel().getSelectedItem() != "") {
            userService.getValues(cb_event.getSelectionModel().getSelectedItem(), lb_nazwa, ta_agenda, lb_data, lb_eventId);
        }
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

    public void initialize(URL location, ResourceBundle resources) {
        cb_uczestnictwo.setItems(participation);
        cb_wyzywienie.setItems(catering);
        userService = new UserService();
        adminService = new AdminService();
        adminService.fillCB(options);
        cb_event.setItems(options);
    }



    public void setText(String name, String id) {
        user_name.setText(name);
        user_id.setText(id);
    }

}