package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        System.out.println("Table created");
    }

    @Override
    public void dropUsersTable() {
        System.out.println("Table droped");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        System.out.println("User saved");
    }

    @Override
    public void removeUserById(long id) {
        System.out.println("User deleted");
    }

    @Override
    public List<User> getAllUsers() {

        System.out.println("All users");
        return null;
    }

    @Override
    public void cleanUsersTable() {
        System.out.println("Table cleaned");
    }
}
