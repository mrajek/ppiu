package service;

import controller.LoginController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminService {
    public boolean remove(String login) throws SQLException {
        PreparedStatement preparedStatement = LoginController.connection.prepareStatement("DELETE FROM user WHERE login = ?");
        preparedStatement.setString(1,login);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Usunieto poprawnie");
            return true;
        } else {
            System.out.println("Błąd");
            return false;
        }
    }

    public  boolean resetPass(String login) throws SQLException {
        PreparedStatement preparedStatement = LoginController.connection.prepareStatement("UPDATE user SET password = 1234 WHERE login = ?");
        preparedStatement.setString(1,login);
        Integer i = preparedStatement.executeUpdate();
        if(i == 1){
            System.out.println("Zresetowano haslo");
            return true;
        } else {
            System.out.println("Błąd");
            return false;
        }
    }
}
