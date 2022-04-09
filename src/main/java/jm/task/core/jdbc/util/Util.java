package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {

    private static final String URLDB = "jdbc:mysql://127.0.0.1:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    static Connection connection = null;

    private Util() {
    }


    public static void openConnection () {
        //Connection connection = null;

        try {
            connection = DriverManager.getConnection(URLDB, USERNAME, PASSWORD);
            System.out.println("Подключение к БД выполнено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Util.connection = connection;
    }


    public static Connection getConnection () {
        /*Connection connection = null;

        try {
            connection = DriverManager.getConnection(URLDB, USERNAME, PASSWORD);
            System.out.println("Подключение к БД выполнено");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return Util.connection;
    }
}
