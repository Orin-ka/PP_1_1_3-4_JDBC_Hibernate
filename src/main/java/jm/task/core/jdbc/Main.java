package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Util.openConnection();

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.createUsersTable();
        userService.saveUser("Lui", "De'Fines",  (byte)69);
        userService.removeUserById(6);
        userService.cleanUsersTable();
        //userService.saveUser("Antonio", "Banderas",  (byte)69);
        /*userService.dropUsersTable();
        userService.dropUsersTable();*/

        try {
            Util.getConnection().close();
            System.out.println("Подключение закрыто");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
