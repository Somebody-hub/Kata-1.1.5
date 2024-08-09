package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String sqlRequest = "CREATE TABLE IF NOT EXISTS tableUsers (" +
                "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(30) NOT NULL," +
                " lastName VARCHAR(30) NOT NULL," +
                " age TINYINT NOT NULL)";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sqlRequest = "DROP TABLE IF EXISTS tableUsers";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlRequest = "INSERT INTO tableUsers (name, lastName, age) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlRequest = "DELETE FROM tableUsers WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlRequest = "SELECT * FROM tableUsers";

        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlRequest = "TRUNCATE TABLE tableUsers";
        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
