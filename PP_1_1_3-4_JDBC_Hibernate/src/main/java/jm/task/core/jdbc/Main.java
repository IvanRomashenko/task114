package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
//        Util util = new Util();
//        Util.getConnection();
        userService.createUsersTable();
        userService.saveUser("name1", "last name1", (byte) 1);
        userService.saveUser("name2", "last name2", (byte) 2);
        userService.saveUser("name3", "last name3", (byte) 3);
        userService.saveUser("name4", "last name4", (byte) 4);
        userService.removeUserById(1);
        userService.dropUsersTable();
        userService.getAllUsers();
        userService.cleanUsersTable();
    }
}
