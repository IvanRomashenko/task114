package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
   // final static String DRIVER = "org.sql.Driver";
    //final static String URL = "jdbc:mysql://localhost:127.0.0.1:3306/pp144";
    final static String URL = "jdbc:mysql://localhost:3306/pp114";

    final static String USERNAME = "root";
    final static String PASSWORD = "root";
    private static Connection connection;
   // private static Properties properties;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Connection");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return connection;
    }


//    public static Properties getProperties() {
//        if (properties == null) {
//            properties = new Properties();
//            properties.setProperty("root", USERNAME);
//            properties.setProperty("root", PASSWORD);
//        }
//        return properties;
//    }

//    public void disconnect() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}