package service;


import controller.StartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SingUpService {
    public boolean singUp(String name, String lastname, String gender, String email, String login, String password) throws SQLException {
        PreparedStatement preparedStatement = StartController.connection.prepareStatement("INSERT INTO user(name, lastname, gender, email, login, password)  VALUES(?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastname);
        preparedStatement.setString(3,gender);
        preparedStatement.setString(4,email);
        preparedStatement.setString(5,login);
        preparedStatement.setString(6,password);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Zarejestrowano poprawnie: ");
            return true;
        } else {
            System.out.println("Błąd rejestracji");
            return false;
        }
    }

    public void changeStage() throws IOException {
        Stage backStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/startView.fxml"));
        backStage.setTitle("SalvAction");
        backStage.setScene(new Scene(root));
        backStage.show();
    }

    public void sendMail() {
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
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("mrajek9@gmail.com"));
            message.setSubject("First Email");
            message.setText("Test");

            Transport.send(message);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
