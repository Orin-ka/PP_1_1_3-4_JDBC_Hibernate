package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String query = """
                CREATE TABLE IF NOT EXISTS users (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(80),
                lastname VARCHAR(80),
                age TINYINT(100));
                """;

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            statement.execute(query);
            connection.commit();
            System.out.println("Таблица users создана");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch(SQLException se2){
                se2.printStackTrace();
            }
            e.printStackTrace();

        }

    }

    public void dropUsersTable() {

        String query = """
                DROP TABLE IF EXISTS users;
                """;

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(query);
            connection.commit();
            System.out.println("Таблица users удалена");
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String query = """
            INSERT into users (name, lastName, age) 
            VALUES (?, ?, ?);
            """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String query = """
                DELETE from users where id=?;
                """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
            System.out.println("User с id – " + id + " удален из базы данных");
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers()  {

        long id;
        String name;
        String lastName;
        Byte age;

        List<User> userList = new ArrayList<>();
        String query = """
            SELECT * FROM users; 
            """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                id = resultSet.getLong(1);
                name = resultSet.getString(2);
                lastName = resultSet.getString(3);
                age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                userList.add(user);

            }
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            e.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {

        dropUsersTable();
        createUsersTable();
        System.out.println("Таблица users очищена");

    }
}
