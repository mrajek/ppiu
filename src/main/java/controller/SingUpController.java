package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class SingUpController {

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
    private ComboBox<?> cb_position;

    @FXML
    private RadioButton rb_sexFemale;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton rb_sexMale;

    @FXML
    private Button bt_register;

    @FXML
    private Button bt_back;

    @FXML
    private Button bt_showPass;

    @FXML
    private TextField tf_newPass;

    @FXML
    private ImageView imgShow;

    @FXML
    void BackAction(ActionEvent event) throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/startView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        Stage registerStage = (Stage) bt_back.getScene().getWindow();
        registerStage.close();
    }

    @FXML
    void SingUpAction(ActionEvent event) {

    }
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
}
