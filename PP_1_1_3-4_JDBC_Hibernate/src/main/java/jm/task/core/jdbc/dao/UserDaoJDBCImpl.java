package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = "create table if not exists user (id bigint primary key auto_increment, name varchar(45), lastName varchar(45), age smallint)";
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }

    }

    public void dropUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = "drop table if exists user";
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Table delete");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "insert into user(name, lastName, age) value(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            connection.commit();
            preparedStatement.executeUpdate();
            System.out.println("User with name - " + name + " add to the database");

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "delete from user where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User delete");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "select * from user";
        List<User> list = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            connection.setAutoCommit(false);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                connection.commit();
                System.out.println("Select all users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "truncate table user";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            connection.commit();
            System.out.println("Table clean");

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        }
    }
}
