package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.LoginService;
import service.SingUpService;
import util.DBConnector;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddUserController {

    @FXML
    private PasswordField pf_newPassRepeat;

    @FXML
    private PasswordField pf_newPass;

    @FXML
    private TextField tf_newLogin;

    @FXML
    private TextField tf_firstName;

    @FXML
    private TextField tf_lastName;

    @FXML
    private TextField tf_email;

    @FXML
    private RadioButton rb_sexFemale;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton rb_sexMale;

    @FXML
    private Button bt_register;

    @FXML
    private Button bt_showPass;

    @FXML
    private ImageView imgShow;

    @FXML
    private TextField tf_newPass;


    @FXML
    void ShowPass(MouseEvent event) {
        pf_newPass.setVisible(false);
        tf_newPass.setText(pf_newPass.getText());
        tf_newPass.setVisible(true);
        Image image = new Image("img/showPass.png");
        imgShow.setImage(image);
    }

    @FXML
    void HidePass(MouseEvent event) {
        tf_newPass.setVisible(false);
        pf_newPass.setVisible(true);
        Image image = new Image("img/hidePass.png");
        imgShow.setImage(image);
    }


    @FXML
    void addAction(ActionEvent event) throws SQLException {
        RadioButton selectedRadioButton = (RadioButton) this.sex.getSelectedToggle();
        String gender = selectedRadioButton.getText();
        StartController.singUpService.singUp(this.tf_firstName.getText(), this.tf_lastName.getText(), gender, this.tf_email.getText(), this.tf_newLogin.getText(), this.pf_newPass.getText());
    }
}
