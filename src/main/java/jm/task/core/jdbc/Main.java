package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {


    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Louis", "de Funès",  (byte)80);
        userService.saveUser("Gerard", "Depardieu",  (byte)73);
        userService.saveUser("Charlie", "Chaplin",  (byte)33);
        userService.saveUser("Brigitte", "Bardot",  (byte)87);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
        System.out.println("Подключение закрыто");
   }
}
