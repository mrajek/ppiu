package controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

}