package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {

    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    private Util() {
    }

    public static Connection getConnection () {

        try {
            connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            System.out.println("Подключение к БД выполнено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
