package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.SingUpService;



import java.io.IOException;
import java.sql.SQLException;


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

    private static SingUpService singUpService;

    @FXML
    void BackAction(ActionEvent event) throws Exception {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/startView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
        StartController.loginService.closeStage(bt_back);
    }

    @FXML
    void SingUpAction(ActionEvent event) throws IOException, SQLException {
        RadioButton selectedRadioButton = (RadioButton) this.sex.getSelectedToggle();
        String gender = selectedRadioButton.getText();
        if(tf_firstName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Nie podano email'a");
            return;
        }
        if(!tf_firstName.getText().matches("^[A-Z][a-z]+")){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Imię musi zaczynać się z wielkiej litery i nie powinno zawierać cyfr!");
            return;
        }
        if(tf_lastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Nie podano nazwiska!");
            return;
        }
        if(!tf_firstName.getText().matches("^[A-Z][a-z]+")){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Nazwisko musi zaczynać się z wielkiej litery i nie powinno zawierać cyfr!");
            return;
        }
        if(!(rb_sexMale.isSelected() || rb_sexFemale.isSelected())){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Wybierz płeć");
            return;
        }

        if (tf_email.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Podaj maila!");
            return;
        }

        if (!tf_email.getText().matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Taki mail nie istenieje!");
            return;
        }

        if(tf_newLogin.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Podaj Login");
            return;
        }

        if(pf_newPass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Podaj hasło!");
            return;
        }
        if(pf_newPass.getText().length() < 8) {
            showAlert(Alert.AlertType.WARNING, "Błąd danych", "Hasło powinno zawierać conajmniej 8 znaków");
            return;
        }
        if(pf_newPassRepeat.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Potwierdz hasło!");
            return;
        }
        if(!pf_newPass.getText().equals(pf_newPassRepeat.getText())) {
            showAlert(Alert.AlertType.ERROR, "Błąd danych", "Hasła są różne!");
            return;
        }

        singUpService.singUp(this.tf_firstName.getText(), this.tf_lastName.getText(), gender, this.tf_email.getText(), this.tf_newLogin.getText(), this.pf_newPass.getText());
        singUpService.sendMail(tf_email.getText());
        showAlert(Alert.AlertType.INFORMATION, "Dokonano rejestracji", "Na podanego maila został wysłąny mail w celu potwierdzenia rejestracji!");
        singUpService.changeStage();
        StartController.loginService.closeStage(bt_register);
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

    public void initialize(){
        singUpService = new SingUpService();

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}