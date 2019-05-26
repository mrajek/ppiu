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


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

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

    public void sendMail(String mail) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            final String ac = "kacwalski2@gmail.com";
            final String pass = "volume12134";
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(ac, pass);
                }
            });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ac));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            message.setSubject("First Email");
            message.setText("Test");

            Transport.send(message);
            System.out.println("Done");
        } catch (Exception e){
            System.out.println(e);
        }
    }

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
        StartController.singUpService.singUp(this.tf_firstName.getText(), this.tf_lastName.getText(), gender, this.tf_email.getText(), this.tf_newLogin.getText(), this.pf_newPass.getText());
        sendMail(tf_email.getText());
        StartController.singUpService.changeStage();
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
}