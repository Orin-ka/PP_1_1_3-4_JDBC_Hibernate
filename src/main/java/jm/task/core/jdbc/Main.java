package jm.task.core.jdbc;


import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //String query = "USE test";
        String query = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, \n" +
                "name VARCHAR(80), lastname VARCHAR(80), age TINYINT);";

        String queryInsertUser1 = "INSERT INTO users(name, lastname, age) VALUES ('Alex', 'Marshall', '18' );";// +
                //String queryInsertUser1 "INSERT INTO users(name, lastname, age) VALUES ('Olga', 'Ivanova', '31' );" +
               // "INSERT INTO users(name, lastname, age) VALUES ('Bill', 'Wells', '21' );" +
               // "INSERT into users(name, lastname, age) VALUES ('Antonio', 'Banderas', '58' );";
        try (Connection connection = Util.getConnection()) {

            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Таблица users создана");
            statement.execute(queryInsertUser1);
            System.out.println("4 юзера добавлены в таблицу users");

            //ResultSet resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
