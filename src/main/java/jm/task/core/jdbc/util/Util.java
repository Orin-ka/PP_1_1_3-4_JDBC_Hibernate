package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String urlDB = "jdbc:mysql://127.0.0.1:3306/test";
    private final static String user = "root";
    private final static String password = "root";

    public static Connection getConnection () {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlDB, user, password);
            System.out.println("Подключение к БД выполнено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
