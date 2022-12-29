package jm.task.core.jdbc.util;

import net.bytebuddy.asm.Advice;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    final static String URL = "jdbc:mysql://localhost:3306/pp144";
    final static String USERNAME = "root";
    final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;


        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено " + e.getMessage());
        }
        if (connection != null) {
            System.out.println("Соединение установлено");
        } else {
            System.out.println("Ошибка соединения");
        }
        try {
            //assert connection != null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
