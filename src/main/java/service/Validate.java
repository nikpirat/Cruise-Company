package service;


import dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service class which validate existing of user using login or login with password
 */


public class Validate {

    /**
     * check using login and password if user is exist
     *
     * @param login    user login from cabinet
     * @param password user password form cabinet
     * @return isExist If user was found - return true , else false
     */

    public boolean checkUser(String login, String password) {
        boolean isExist = false;
        String sql = "Select * from user_role as u where u.login = ? and u.password = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, login);
            insertStatement.setString(2, password);
            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) isExist = true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    /**
     * check using login and password if user is exist
     *
     * @param login user login from cabinet
     * @return isExist If user was found - return true , else false
     */


    public boolean checkLogin(String login) {
        boolean status = false;
        String sql = "Select * from user_role as ur where ur.login = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setString(1, login);
            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) status = true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
