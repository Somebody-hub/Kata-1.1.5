package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.List;

public class Main {

  /*  public class TestConnection {
        public static final String USER_NAME = "root";
        public static final String PASSWORD = "admin";
        public static final String URL = "jdbc:mysql://localhost:3306/KataPP114";
        public static Statement statement;
        public static Connection connection;

        static {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException();
            }
        }

        static {
            try {
                statement = connection.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
*/
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        for (int i = 0; i < 4; i++){
            userDao.saveUser("Name" + i, "LastName" + i, (byte) (20 + i * 5));
            System.out.println("User с именем - " + "Name" + i + " добавлен в базу данных");
        }

        userDao.removeUserById(1);
        List<User> usList = userDao.getAllUsers();
        System.out.println(usList);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.closeConnection();
    }

}
